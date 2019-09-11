package relatorios;

import auxiliar.EstudanteRelatorio;
import java.io.IOException;
import java.text.ParseException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import negocio.Estudante;
import negocio.Servidor;
import negocio.VisitaTecnica;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "gerarRelatorioEstudantes")
@SessionScoped
public class GerarRelatorioEstudantes implements Serializable{

    public static void gerarRelatorio(List<EstudanteRelatorio> estudantes) throws JRException, ParseException, IOException {

        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\ControleVisitaTec\\src\\java\\relatorios\\relatorioEstudantes.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(estudantes));

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioEstudantes.pdf");

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        ServletContext servletContext = (ServletContext) externalContext
                .getContext();

        ServletOutputStream servletOutputStream = httpServletResponse
                .getOutputStream();

        byte[] bytes = JasperExportManager.exportReportToPdf(print);

        httpServletResponse.setContentLength(bytes.length);
        httpServletResponse.getOutputStream().write(bytes, 0, bytes.length);
        httpServletResponse.getOutputStream().flush();
        httpServletResponse.getOutputStream().close();
        FacesContext.getCurrentInstance().responseComplete();
        JasperExportManager.exportReportToPdfStream(print,
                servletOutputStream);
        JasperExportManager.exportReportToPdfFile(print,
                "Relatório_Visita_Tecnica_Estudantes.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void recuperarEstudantes(VisitaTecnica visita, Servidor servidor) throws JRException, ParseException, IOException {
        List<Estudante> listaEstudantes= visita.getEstudantes();
        List<EstudanteRelatorio> listaRelatorio = new ArrayList<EstudanteRelatorio>();
        Integer cont = 1;
        for (Estudante estudante : listaEstudantes) {
            EstudanteRelatorio e = new EstudanteRelatorio();
            e.setCpf(estudante.getCpf());
            e.setNome(estudante.getNome());
            e.setAgencia(estudante.getAgencia());
            e.setConta(estudante.getConta());
            e.setCpf(estudante.getCpf());
            e.setDataNascimento(estudante.getDataNascimento());
            e.setObservacao(estudante.getObservacao());
            e.setServidor(servidor);
            if(estudante.getBanco() == null){
                e.setBanco("-");
            } else {
                e.setBanco(estudante.getBanco().getNome());
            }
            e.setId(cont);
            cont++;
            listaRelatorio.add(e);
        }
        gerarRelatorio(listaRelatorio);
    }    
}
