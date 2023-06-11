package dao;

import model.Produto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CadastraXLSDAO implements ICadastraDAO {
    private static final String FILE_PATH = "dbxlsx.xlsx";
    private static final String SHEET_NAME = "produtos";

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public CadastraXLSDAO() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileInputStream);
            } else {
                workbook = new XSSFWorkbook();
            }

            sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) {
                sheet = workbook.createSheet(SHEET_NAME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        int lastRowNum = sheet.getLastRowNum();
        Row newRow = sheet.createRow(lastRowNum + 1);

        Cell idCell = newRow.createCell(0);
        idCell.setCellValue(produto.getId());

        Cell nomeCell = newRow.createCell(1);
        nomeCell.setCellValue(produto.getNome());

        Cell descricaoCell = newRow.createCell(2);
        descricaoCell.setCellValue(produto.getDescricao());

        Cell precoCell = newRow.createCell(3);
        precoCell.setCellValue(produto.getPreco());

        Cell qtdCell = newRow.createCell(4);
        qtdCell.setCellValue(produto.getQtd());
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Pula o cabeçalho
            }

            int id = (int) row.getCell(0).getNumericCellValue();
            String nome = row.getCell(1).getStringCellValue();
            String descricao = row.getCell(2).getStringCellValue();
            double preco = row.getCell(3).getNumericCellValue();
            int qtd = (int) row.getCell(4).getNumericCellValue();

            Produto produto = new Produto(id, nome, descricao, preco, qtd);
            produtos.add(produto);
        }

        return produtos;
    }

    @Override
    public void salvarDados() {
        try {
            File file = new File(FILE_PATH);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}