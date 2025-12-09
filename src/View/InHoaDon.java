/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

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
public class InHoaDon {

    public static void taoHoaDon(Model.ModelHoaDon hd, ArrayList<ModelHoaDonChiTiet> listHDCT, Model.Model_DangNhap nv) {
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
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Thông tin khách hàng", fontTieuDe1));
            document.add(new Paragraph("Họ tên: " + hd.getTenKhachHang(), fontNoiDung));
            document.add(new Paragraph("Số điện thoại: " + hd.getSDT(), fontNoiDung));
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
            for (ModelHoaDonChiTiet sp : listHDCT) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(sp.getId()), fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(sp.getTenSanPham()), fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(sp.getSoLuong()), fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(sp.getDonGia()) + " VNĐ", fontNoiDung)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(sp.getTongTien()) + " VNĐ", fontNoiDung)));
            }

            document.add(table);
            document.add(new Paragraph("Tổng tiền tạm tính: " + formatter.format(hd.getTongTien()) + " VNĐ", fontTieuDe1));
            document.add(new Paragraph("Khuyến mãi: " + (hd.getGiaTriGiam() != null ? formatter.format(hd.getGiaTriGiam()) : "0") + " VNĐ", fontTieuDe1));
            document.add(new Paragraph("Thanh toán: " + formatter.format(hd.getGiaSauGiam()) + "VNĐ", fontTieuDe1));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Tên người bán", fontTieuDe1));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(nv.getTenNhanVien(), fontNoiDung));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
