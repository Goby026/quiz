package dev.server.quiz.view.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dev.server.quiz.entities.CategoriaItem;
import dev.server.quiz.entities.Consolidado;
import dev.server.quiz.entities.Ficha;
import dev.server.quiz.entities.FichaItem;
import dev.server.quiz.services.CategoriaItemService;
import dev.server.quiz.services.ConsolidadoService;
import dev.server.quiz.services.FichaItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("pages/consolidados/formulario")
public class ConsolidadoPdf extends AbstractPdfView {

    private final FichaItemService fichaItemService;
    private final CategoriaItemService categoriaItemService;
    private final ConsolidadoService consolidadoService;

    public ConsolidadoPdf(FichaItemService fichaItemService, CategoriaItemService categoriaItemService, ConsolidadoService consolidadoService) {
        this.fichaItemService = fichaItemService;
        this.categoriaItemService = categoriaItemService;
        this.consolidadoService = consolidadoService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String TITULO = "FICHA DE MONITOREO VIRTUAL A LA PRÁCTICA PEDAGÓGICA EN EL MARCO DE LA EDUCACIÓN A DISTANCIA " +
                "EBR APRENDO EN CASA 2023";

        try {

            Ficha ficha = (Ficha) model.get("ficha");

            document.open();
            document.addTitle("FICHA DE MONITOREO VIRTUAL");

//            ********TAMAÑO DE FUENTE DE TITULO********
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(14);

//            ********TAMAÑO DE FUENTE DE SUBTITULOS********
            Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontSubtitle.setSize(11);

            Paragraph paragraph = new Paragraph(TITULO, fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            paragraph.setSpacingAfter(30L);

//            ********I. IE********
            Paragraph subI = new Paragraph("I. DATOS DE LA IE", fontSubtitle);
            subI.setAlignment(Paragraph.ALIGN_LEFT);
            subI.setSpacingAfter(10L);

            PdfPTable tblIE = new PdfPTable(4);
            tblIE.setWidthPercentage(100L);

            tblIE.addCell(cellHeaderStyle("Nombre de la IE"));
            tblIE.addCell(fuenteCelda(ficha.getActividad().getInstitucion().getNombre()));
            tblIE.addCell(cellHeaderStyle("Lugar"));
            tblIE.addCell(fuenteCelda( ficha.getActividad().getInstitucion().getLugar()) );

            PdfPTable tblIE2 = new PdfPTable(6);
            tblIE2.setWidthPercentage(100L);
            tblIE2.addCell( cellHeaderStyle("DRE") );
            tblIE2.addCell( fuenteCelda(ficha.getActividad().getInstitucion().getDre().getDescripcion()) );
            tblIE2.addCell( cellHeaderStyle("UGEL") );
            tblIE2.addCell( fuenteCelda(ficha.getActividad().getInstitucion().getUgel().getDescripcion()) );
            tblIE2.addCell( cellHeaderStyle("Nivel y/o modalidad") );
            tblIE2.addCell( fuenteCelda(ficha.getActividad().getNivel().getDescripcion()) );

//            ********II. DATOS DEL MONITOR ********
            Paragraph subII = new Paragraph("II. DATOS DEL MONITOR", fontSubtitle);
            subII.setAlignment(Paragraph.ALIGN_LEFT);
            subII.setSpacingAfter(10L);

            PdfPTable tblSubII = new PdfPTable(4);
            tblSubII.setWidthPercentage(100L);
//            ------ row 1 ------
            tblSubII.addCell(cellHeaderStyle("Nombre del monitor"));
            tblSubII.addCell(cellHeaderStyle("Apellidos del monitor"));
            tblSubII.addCell(cellHeaderStyle("Teléfono"));
            tblSubII.addCell(cellHeaderStyle("DNI"));
//            ------ row 2 ------
            tblSubII.addCell(fuenteCelda(ficha.getUsuario()));
            tblSubII.addCell(fuenteCelda(ficha.getUsuario()));
            tblSubII.addCell(fuenteCelda(ficha.getUsuario()));
            tblSubII.addCell(fuenteCelda(ficha.getUsuario()));
//            ------ row 3 ------
            PdfPTable tblSubII3 = new PdfPTable(4);
            tblSubII3.setWidthPercentage(100L);
            tblSubII3.addCell(cellHeaderStyle("Cargo"));
            tblSubII3.addCell(fuenteCelda(ficha.getUsuario()));
            tblSubII3.addCell(cellHeaderStyle("Correo electrónico"));
            tblSubII3.addCell(fuenteCelda(ficha.getUsuario()));
//            ------ row 4 ------
            PdfPTable tblSubII4 = new PdfPTable(8);
            tblSubII4.setWidthPercentage(100L);
            tblSubII4.addCell(cellHeaderStyle("Número de visita a la IE"));
            tblSubII4.addCell(fuenteCelda(ficha.getActividad().getNumVisita()));
            tblSubII4.addCell(cellHeaderStyle("Fecha de aplicación"));
            tblSubII4.addCell(fuenteCelda(ficha.getCreatedAt().toString()));
            tblSubII4.addCell(cellHeaderStyle("Hora de inicio"));
            tblSubII4.addCell(fuenteCelda(ficha.getActividad().getHora_inicio().toString()));
            tblSubII4.addCell(cellHeaderStyle("Hora de fin"));
            tblSubII4.addCell(fuenteCelda(ficha.getActividad().getHora_fin().toString()));
//            ------ row 5 ------
            PdfPTable tblSubII5 = new PdfPTable(2);
            tblSubII5.setWidthPercentage(100L);
            tblSubII5.addCell(cellHeaderStyle("Canal de comunicación para el monitoreo"));
            tblSubII5.addCell(fuenteCelda(ficha.getActividad().getCanal().getDescripcion()));

//            ********III. DATOS DEL DOCENTE MONITOREADO ********
            Paragraph subIII = new Paragraph("III. DATOS DEL DOCENTE MONITOREADO", fontSubtitle);
            subIII.setAlignment(Paragraph.ALIGN_LEFT);
            subIII.setSpacingAfter(10L);

            PdfPTable tblSubIII = new PdfPTable(3);
            tblSubIII.setWidthPercentage(100L);
//            ------ row 1 ------
            tblSubIII.addCell(cellHeaderStyle("Nombres completos"));
            tblSubIII.addCell(cellHeaderStyle("Apellidos completos"));
            tblSubIII.addCell(cellHeaderStyle("DNI"));
//            ------ row 2 ------
            tblSubIII.addCell(fuenteCelda(ficha.getActividad().getDocente().getNombres()));
            tblSubIII.addCell(fuenteCelda(ficha.getActividad().getDocente().getApellidos()));
            tblSubIII.addCell(fuenteCelda(ficha.getActividad().getDocente().getDni()));

            PdfPTable tblSubIII2 = new PdfPTable(4);
            tblSubIII2.setWidthPercentage(100L);
//            ------ row 3 ------
            tblSubIII2.addCell(cellHeaderStyle("Correo Electrónico"));
            tblSubIII2.addCell(fuenteCelda(ficha.getActividad().getDocente().getCorreo()));
            tblSubIII2.addCell(cellHeaderStyle("Teléfono"));
            tblSubIII2.addCell(fuenteCelda(ficha.getActividad().getDocente().getTelefono()));

//            ********IV. INFORMACIÓN DE LA PROGRAMACIÓN DE LA ACTIVIDAD O SESIÓN ********
            Paragraph subIV = new Paragraph("IV. INFORMACIÓN DE LA PROGRAMACIÓN DE LA ACTIVIDAD O SESIÓN", fontSubtitle);
            subIV.setAlignment(Paragraph.ALIGN_LEFT);
            subIV.setSpacingAfter(10L);

            PdfPTable tblSubIV = new PdfPTable(6);
            tblSubIV.setWidthPercentage(100L);
//            ------ row 1 ------
            tblSubIV.addCell(cellHeaderStyle("Ciclo"));
            tblSubIV.addCell(fuenteCelda(ficha.getActividad().getCiclo()));
            tblSubIV.addCell(cellHeaderStyle("Grado y seccion"));
            tblSubIV.addCell(fuenteCelda(ficha.getActividad().getGradoSeccion()));
            tblSubIV.addCell(cellHeaderStyle("Cantidad de de estudiantes que participan AeC"));
            tblSubIV.addCell(fuenteCelda(ficha.getActividad().getCantidadEstudiantes()));
//            ------ row 2 ------
            tblSubIV.addCell(cellHeaderStyle("N° semana"));
            tblSubIV.addCell(fuenteCelda(ficha.getActividad().getNumSemana()));
            tblSubIV.addCell(cellHeaderStyle("N° de sesión y/o Actividad"));
            tblSubIV.addCell(fuenteCelda(ficha.getActividad().getNumActividad()));
            tblSubIV.addCell(cellHeaderStyle("Cuenta con el directorio de los PP.FF. (EBR, EBA menos de edad)"));
            tblSubIV.addCell(fuenteCelda((ficha.getActividad().isDirectorioPadres())? "SI":"NO"));
//            ------ row 3 ------
            PdfPTable tblSubIV3 = new PdfPTable(4);
            tblSubIV3.setWidthPercentage(100L);

            tblSubIV3.addCell(cellHeaderStyle("Área"));
            tblSubIV3.addCell(fuenteCelda(ficha.getActividad().getArea().getNombreCurso()));
            tblSubIV3.addCell(cellHeaderStyle("Cuenta con el directorio de los estudiantes"));
            tblSubIV3.addCell(fuenteCelda((ficha.getActividad().isDirectorioEstudiantes())?"SI":"NO"));
//            ------ row 4 ------
            PdfPTable tblSubIV4 = new PdfPTable(2);
            tblSubIV4.setWidthPercentage(100L);

            tblSubIV4.addCell(cellHeaderStyle("Competencia (s)"));
            tblSubIV4.addCell(fuenteCelda(ficha.getActividad().getCompetencia()));
//            ------ row 5 ------
            tblSubIV4.addCell(cellHeaderStyle("Nombre de la sesión o Actividad"));
            tblSubIV4.addCell(fuenteCelda(ficha.getActividad().getNombreActividad()));

//            ********V. DESARROLLO DE LA ACTIVIDAD O SESIÓN DE APRENDIZAJE ********
            Paragraph subV = new Paragraph("V. DESARROLLO DE LA ACTIVIDAD O SESIÓN DE APRENDIZAJE", fontSubtitle);
            subV.setAlignment(Paragraph.ALIGN_LEFT);
            subV.setSpacingAfter(10L);

            PdfPTable tblSubV = new PdfPTable(1);
            tblSubV.setWidthPercentage(100L);
//            ------ row 1 ------
            tblSubV.addCell(cellHeaderStyle("Recursos y/o medios utilizados para el desarrollo de las actividades de " +
                    "aprendizaje en la estrategia Aprendo en Casa"));
//            ------ row 2 ------
            PdfPTable tblSubV2 = new PdfPTable(4);
            tblSubV2.setWidthPercentage(100L);

            tblSubV2.addCell(cellHeaderStyle("PLATAFORMA WEB TABLETA-MED"));
            tblSubV2.addCell(cellHeaderStyle("TV"));
            tblSubV2.addCell(cellHeaderStyle("RADIO"));
            tblSubV2.addCell(cellHeaderStyle("OTRO MEDIO: ..........."));

//            ------ row 3 ------
            PdfPTable tblSubV3 = new PdfPTable(8);
            tblSubV3.setWidthPercentage(100L);

            tblSubV3.addCell(cellHeaderStyle("Cantidad"));
            tblSubV3.addCell(cellHeaderStyle("%"));
            tblSubV3.addCell(cellHeaderStyle("Cantidad"));
            tblSubV3.addCell(cellHeaderStyle("%"));
            tblSubV3.addCell(cellHeaderStyle("Cantidad"));
            tblSubV3.addCell(cellHeaderStyle("%"));
            tblSubV3.addCell(cellHeaderStyle("Cantidad"));
            tblSubV3.addCell(cellHeaderStyle("%"));

//            ------ row 4 ------
            PdfPTable tblSubV4 = new PdfPTable(8);
            tblSubV4.setWidthPercentage(100L);

            tblSubV4.addCell(String.valueOf( ficha.getActividad().getCantidadMedio() ));
            tblSubV4.addCell(fuenteCelda("0.0%"));
            tblSubV4.addCell(String.valueOf( ficha.getActividad().getCantidadMedio() ));
            tblSubV4.addCell(fuenteCelda("0.0%"));
            tblSubV4.addCell(String.valueOf( ficha.getActividad().getCantidadMedio() ));
            tblSubV4.addCell(fuenteCelda("0.0%"));
            tblSubV4.addCell(String.valueOf( ficha.getActividad().getCantidadMedio() ));
            tblSubV4.addCell(fuenteCelda("0.0%"));
            tblSubV4.setSpacingAfter(40L);

//            ********VI. ACOMPAÑAMIENTO DE LA SESIÓN O ACTIVIDAD DE APRENDIZAJE ********
            Paragraph subVI = new Paragraph("VI. ACOMPAÑAMIENTO DE LA SESIÓN O ACTIVIDAD DE APRENDIZAJE", fontSubtitle);
            subVI.setAlignment(Paragraph.ALIGN_LEFT);
            subVI.setSpacingAfter(10L);

            PdfPTable tblSubVIDesc = new PdfPTable(1);
            tblSubVIDesc.setWidthPercentage(100L);
//            ------ tabla de descripcion ------
            tblSubVIDesc.addCell(fuenteCelda("Estimado monitor, la ficha de monitoreo contiene 20 ítems, los primeros" +
                    " 17 ítems son para docentes de EBR/EBA que no cuentan con estudiantes con necesidades educativas especiales (NEE) asociadas a discapacidad.\n" +
                    "Los 18 items, en general, son para docentes y directivos (EBR/EBA) que atienden a estudiantes con necesidades educativas especiales (NEE) asociadas a discapacidad.\n" +
                    "El objetivo es recoger información de la práctica pedagógica para identificar los avances y " +
                    "dificultades y brindar la asesoría pedagógica para la mejora de los aprendizajes de los estudiantes. Esta ficha será aplicada por el monitor (directivo de la IE o especialista de educación) durante el monitoreo y seguimiento al docente, para lo cual se debe revisar lo descriptores establecidos en el protocolo de aplicación de este instrumento. Asimismo, se ha considerado la columna de evidencias para valorar la ejecución del ítem y la columna de observaciones para ampliar la información que se recoge. Considerar la valoración de 30 puntos que corresponde al 100% y 36 puntos para IIEE que atienden a estudiantes con necesidades educativas especiales (NEE) asociadas a discapacidad.\n"));
            tblSubVIDesc.setSpacingAfter(20L);

//            ------ tabla de leyenda de valores ------
            PdfPTable tblSubVILey = new PdfPTable(3);
            tblSubVILey.setWidthPercentage(50L);
//            ------ tabla de descripcion ------
            tblSubVILey.addCell(cellHeaderStyle("NO"));
            tblSubVILey.addCell(cellHeaderStyle("PROCESO"));
            tblSubVILey.addCell(cellHeaderStyle("SI"));
//            ------ valores ------
            tblSubVILey.addCell(fuenteCelda(String.valueOf(0)));
            tblSubVILey.addCell(fuenteCelda(String.valueOf(1)));
            tblSubVILey.addCell(fuenteCelda(String.valueOf(2)));

            tblSubVILey.setHorizontalAlignment(0);
            tblSubVILey.setSpacingAfter(20L);

//            ********VALORACIÓN DE ITEMS ********
            Paragraph subValoracion = new Paragraph("VALORACIÓN DE ITEMS", fontSubtitle);
            subValoracion.setAlignment(Paragraph.ALIGN_LEFT);
            subValoracion.setSpacingAfter(10L);

            PdfPTable tblValoracion = new PdfPTable(5);
            tblValoracion.setWidthPercentage(100L);
//            ------ row 1 ------
            tblValoracion.addCell(cellHeaderStyle("N°"));
            tblValoracion.addCell(cellHeaderStyle("Items"));
            tblValoracion.addCell(cellHeaderStyle("Valoración"));
            tblValoracion.addCell(cellHeaderStyle("Evidencias"));
            tblValoracion.addCell(cellHeaderStyle("Observaciones"));

            document.add(paragraph);
//            I
            document.add(subI);
            document.add(tblIE);
            document.add(tblIE2);
//            II
            document.add(subII);
            document.add(tblSubII);
            document.add(tblSubII3);
            document.add(tblSubII4);
            document.add(tblSubII5);
//            III
            document.add(subIII);
            document.add(tblSubIII);
            document.add(tblSubIII2);
//            IV
            document.add(subIV);
            document.add(tblSubIV);
            document.add(tblSubIV3);
            document.add(tblSubIV4);
//            V
            document.add(subV);
            document.add(tblSubV);
            document.add(tblSubV2);
            document.add(tblSubV3);
            document.add(tblSubV4);
//            VI
            document.add(subVI);
            document.add(tblSubVIDesc);
            document.add(tblSubVILey);
//            VALORACION DE ITEMS
            document.add(subValoracion);
            document.add(tblValoracion);


            PdfPTable tblItem = null;

            List<FichaItem> fichaItemList = fichaItemService.listar(ficha.getId());

            for (CategoriaItem cat: categoriaItemService.listar()) {
//            ------ row 1 ------
                PdfPTable tblCategoria = new PdfPTable(1);
                tblCategoria.setWidthPercentage(100L);
                PdfPCell cellCategoria= new PdfPCell(fuenteCelda(cat.getDescripcion()));
                cellCategoria.setBackgroundColor(new Color(211, 211, 211));
                cellCategoria.setPadding(8f);
                tblCategoria.addCell(cellCategoria);
                document.add(tblCategoria);
                for ( FichaItem fi : fichaItemList) {

//                    item de categoria
                    if (cat.getId() == fi.getItem().getCategoriaItem().getId()){

                        tblItem = new PdfPTable(5);
                        tblItem.setWidthPercentage(100L);

                        tblItem.addCell(fuenteCelda( String.valueOf(fi.getId())));
                        tblItem.addCell(fuenteCelda(fi.getItem().getDescripcion()));
                        tblItem.addCell(fuenteCelda( String.valueOf(fi.getValoracion())));
                        tblItem.addCell(fuenteCelda("Evidencia"));
                        tblItem.addCell(fuenteCelda(fi.getDescripcion()));
                        document.add(tblItem);

                    }
                }
            }

//            ********VII. CONSOLIDADO DE LOS NIVELES DE AVANCE ********
            Paragraph subVII = new Paragraph("VII. CONSOLIDADO DE LOS NIVELES DE AVANCE", fontSubtitle);
            subVII.setAlignment(Paragraph.ALIGN_LEFT);
            subVII.setSpacingAfter(10L);
            document.add(subVII);

            Paragraph vineta = new Paragraph("* Docentes sin estudiantes con NEE");
            vineta.setSpacingAfter(5L);
            document.add(vineta);

            PdfPTable tblSubVII = new PdfPTable(7);
            tblSubVII.setWidthPercentage(100L);
//            ------ row 1 ------
            tblSubVII.addCell(cellHeaderStyle("ASPECTOS MONITOREADOS"));
            tblSubVII.addCell(cellHeaderStyle("ITEMS"));
            tblSubVII.addCell(cellHeaderStyle("NO"));
            tblSubVII.addCell(cellHeaderStyle("PROCESO"));
            tblSubVII.addCell(cellHeaderStyle("SI"));
            tblSubVII.addCell(cellHeaderStyle("TOTAL"));
            tblSubVII.addCell(cellHeaderStyle("NIVEL DE AVANCE %"));
//            ------ row 2 ------
//            PdfPTable tblSubV2 = new PdfPTable(4);
//            tblSubV2.setWidthPercentage(100L);

            document.add(tblSubVII);

//        **********ADD consolidado**********

            List<Consolidado> consolidados = consolidadoService.listarPorFicha(ficha);

            for (Consolidado consolidado: consolidados) {
                PdfPTable tblConsolidado = new PdfPTable(7);
                tblConsolidado.setWidthPercentage(100L);
//            ------ row 1 ------
                tblConsolidado.addCell(fuenteCelda(String.valueOf(consolidado.getCantidadItem())));
                tblConsolidado.addCell(fuenteCelda(String.valueOf(consolidado.getCategoriaItem().getDescripcion())));
                tblConsolidado.addCell(fuenteCelda(String.valueOf(consolidado.getNoCat())));
                tblConsolidado.addCell(fuenteCelda(String.valueOf(consolidado.getProcesoCat())));
                tblConsolidado.addCell(fuenteCelda(String.valueOf(consolidado.getSiCat())));
                tblConsolidado.addCell(fuenteCelda(String.valueOf(consolidado.getTotal())));
                tblConsolidado.addCell(fuenteCelda(String.valueOf( "?" )));

                document.add(tblConsolidado);
            }

//            ********VIII. RECOMENDACIONES ********
            Paragraph subVIII = new Paragraph("VIII. RECOMENDACIONES", fontSubtitle);
            subVIII.setAlignment(Paragraph.ALIGN_LEFT);
            subVIII.setSpacingAfter(10L);
            document.add(subVIII);

            PdfPTable tblRecomendacion = new PdfPTable(1);
            tblRecomendacion.setWidthPercentage(100L);
//            ------ row 1 ------
//            tblRecomendacion.addCell(fuenteCelda(String.valueOf(ficha.getIndicador().getRecomendacion())));
            tblRecomendacion.addCell("EJEMPLO");
            document.add(tblRecomendacion);

//            ********IX. COMPROMISOS ********
            Paragraph subIx = new Paragraph("IX. COMPROMISOS", fontSubtitle);
            subIx.setAlignment(Paragraph.ALIGN_LEFT);
            subIx.setSpacingAfter(10L);
            document.add(subIx);

            PdfPTable tblCabeceraCompromiso = new PdfPTable(2);
            tblCabeceraCompromiso.setWidthPercentage(100L);
//            ------ row 1 ------
            tblCabeceraCompromiso.addCell(cellHeaderStyle("DEL DOCENTE"));
            tblCabeceraCompromiso.addCell(cellHeaderStyle("DEL MONITOR"));
            document.add(tblCabeceraCompromiso);

            PdfPTable tblFirma = new PdfPTable(2);
            tblFirma.setWidthPercentage(100L);
//            ------ row 1 ------
            PdfPCell firma = new PdfPCell();
            firma.setFixedHeight(80L);
            tblFirma.addCell(firma);
            tblFirma.addCell(firma);
            document.add(tblFirma);


        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        }finally {
            document.close();
        }

    }

//    CELDAS PLOMAS
    private PdfPCell cellHeaderStyle(String desc){

//            ********TAMAÑO DE FUENTE DE CELDAS********
        Font estiloFuente = FontFactory.getFont(FontFactory.HELVETICA);
        estiloFuente.setSize(10);

        Phrase frase = new Phrase(desc, estiloFuente);

        PdfPCell celda= new PdfPCell(frase);
        celda.setBackgroundColor(new Color(211, 211, 211));
        celda.setPadding(8f);

        return celda;
    }

    //    CELDAS PLOMAS
    private PdfPCell fuenteCelda(String desc){
        Font estiloFuente = FontFactory.getFont(FontFactory.HELVETICA);
        estiloFuente.setSize(9);

        Phrase frase = new Phrase(desc, estiloFuente);

        PdfPCell celda= new PdfPCell(frase);
//        celda.setBackgroundColor(new Color(211, 211, 211));
        celda.setHorizontalAlignment(1);
        celda.setPadding(8f);

        return celda;
    }

}
