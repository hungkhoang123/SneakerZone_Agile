/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excel;

import Model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import Model.ModelHoaDonChiTiet;
import java.text.DecimalFormat;

/**
 *
 * @author hungb
 */
public class InHoaDon1 {

    public static void taoHoaDon(ModelHoaDonInHoaDon hd, ArrayList<ModelHoaDonChiTiet> listHDCT) {
       
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("hoa_don_" + hd.getMaHoaDon() + ".pdf"));
            document.open();

            BaseFont bf = BaseFont.createFont("src/font/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            DecimalFormat formatter = new DecimalFormat("#,###");

            Font fontTieuDe = new Font(bf, 18, Font.BOLD);
            Font fontTieuDe1 = new Font(bf, 13, Font.BOLD);
            Font fontNoiDung = new Font(bf, 13, Font.NORMAL);

            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", fontTieuDe);
            Paragraph maHD = new Paragraph("Mã hóa đơn: " + hd.getMaHoaDon());
            maHD.setAlignment(Element.ALIGN_RIGHT);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(maHD);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Thông tin khách hàng", fontTieuDe1));
            document.add(new Paragraph("Họ tên: " + hd.getTenKhachHang(), fontNoiDung));
            document.add(new Paragraph("Số điện thoại: " + hd.getSdtKhachHang(), fontNoiDung));
            document.add(new Paragraph("Ngày thanh toán: " + hd.getNgayMuaHang(), fontNoiDung));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Danh sách sản phẩm", fontTieuDe1));
            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Phrase("STT", fontTieuDe1)));
            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontTieuDe1)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", fontTieuDe1)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá", fontTieuDe1)));
            table.addCell(new PdfPCell(new Phrase("Thành tiền", fontTieuDe1)));
            for (int i = 0; i < listHDCT.size(); i++) {
                int a = 0;
                table.addCell(new PdfPCell(new Phrase(String.valueOf(i+1), fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(listHDCT.get(i).getTenSanPham()), fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(listHDCT.get(i).getSoLuong()), fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(listHDCT.get(i).getDonGia()) + " VNĐ", fontNoiDung)));
                a = listHDCT.get(i).getSoLuong() * listHDCT.get(i).getDonGia();
                table.addCell(new PdfPCell(new Phrase(formatter.format(a) + " VNĐ", fontNoiDung)));
            }

            document.add(table);
            document.add(new Paragraph("Tổng tiền tạm tính: " + formatter.format(hd.getTongTien()) + " VNĐ", fontTieuDe1));
            document.add(new Paragraph("Khuyến mãi: " + hd.getGiaGiam()!= null ? formatter.format(hd.getGiaGiam()) : "0" + " VNĐ", fontTieuDe1));
            document.add(new Paragraph("Thanh toán: " + formatter.format(hd.getGiaSauGiam()) + "VNĐ", fontTieuDe1));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Tên người bán", fontTieuDe1));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(hd.getTenNhanVien(), fontNoiDung));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
