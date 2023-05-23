package org.example.additional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelController {
    /**
     Escribe datos en una hoja de cálculo de Excel existente.
     @param id el ID del registro que se desea modificar.
     @param caso el número del caso que se desea modificar.
     @param tamano el nuevo tamaño que se desea asignar al caso.
     @param tiempoRespuesta el nuevo tiempo de respuesta que se desea asignar al caso.
     */

    public static void escribirEnHoja(int id, int caso, String tamano, double tiempoRespuesta) {

        // Cargar el archivo existente de Excel
        String nombreArchivo = "datos.xlsx";
        try (FileInputStream archivoEntrada = new FileInputStream(nombreArchivo)) {
            Workbook libro = new XSSFWorkbook(archivoEntrada);
            //Sheet hoja = libro.createSheet("Javas");
            Sheet hoja = libro.getSheetAt(0);

            // Modificar los datos de la hoja existente
            int filaActual = hoja.getLastRowNum() + 1;

            Object[] nuevosDatos = {
                    tamano, tiempoRespuesta
            };

            for (Row fila : hoja) {
                if (fila.getRowNum() == 0) {
                    // Ignorar la primera fila (encabezados)
                    continue;
                }
                int idActual = (int) fila.getCell(0).getNumericCellValue();
                if (idActual == id) {
                    // Se encontró el ID buscado en la hoja
                    // Se encontró el ID buscado en la hoja
                    Cell celdaValor1 = fila.createCell(caso*2);
                    celdaValor1.setCellValue(tamano+"*"+tamano);
                    Cell celdaValor2 = fila.createCell(caso*2+1);
                    celdaValor2.setCellValue(tiempoRespuesta);
                }
            }
            // Guardar los cambios en el archivo
            try (FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo)) {
                // Actualizar las celdas que dependen de los nuevos datos
                libro.getCreationHelper().createFormulaEvaluator().evaluateAll();
                libro.write(archivoSalida);
                libro.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void escribirEnHojaEspecifica(double valor, int j) {
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream("datos.xlsx"))) {
            Sheet sheet = workbook.getSheet("CasosAnalisis");
            Row row = sheet.getRow(5+j);
            if (row == null) {
                row = sheet.createRow(5+j);
            }
            Cell cell = row.createCell(26);
            cell.setCellValue(valor);

            FileOutputStream outputStream = new FileOutputStream("datos.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
