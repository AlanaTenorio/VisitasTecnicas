package relatorios;

import auxiliar.VisitaRelatorio;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
@ManagedBean(name = "gerarRelatorioVisita")
@SessionScoped
public class GerarRelatorioVisita implements Serializable {

    public static void gerarRelatorio(List<VisitaRelatorio> visita) throws JRException, ParseException, IOException {

        System.out.println("Gerando relatório...");

        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\ControleVisitaTec\\src\\java\\relatorios\\relatorioVisitaTecnica.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(visita));

        System.out.println("Relatório gerado.");

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioVisitaTecnica.pdf");

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
                "Relatório_Visita_Tecnica.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void recuperarVisita(VisitaTecnica visita, Servidor servidor) throws JRException, ParseException, IOException {
        VisitaRelatorio v = new VisitaRelatorio();
        v.setVisita(visita);
        String servidores = "";

        if(!visita.getServidores().isEmpty()){
            for (Servidor aux : visita.getServidores()) {
                servidores = servidores + aux.getNome() + ", ";
            }
            servidores = servidores.substring(0, servidores.length() - 2);    
        } else {
            servidores = "-";
        }
        
        v.setServidores(servidores);
        v.setServidor(servidor);

        List<VisitaRelatorio> vt = new ArrayList<VisitaRelatorio>();
        vt.add(v);
        gerarRelatorio(vt);
    }
}
