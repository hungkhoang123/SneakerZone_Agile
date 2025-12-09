/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author VIETTECH88
 */
import javax.swing.*;
import java.awt.*;

public class TransparentSpinnerWithBottomBorder {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("JSpinner with Bottom Border");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Tạo JSpinner
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        // Làm JSpinner trong suốt và thêm border bottom
        spinner.setOpaque(false); // Tắt nền
        spinner.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Chỉ vẽ đường dưới

        // Tùy chỉnh editor (JFormattedTextField) bên trong JSpinner
//        JComponent editor = spinner.getEditor();
//        if (editor instanceof JSpinner.DefaultEditor) {
//            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
//            JFormattedTextField textField = spinnerEditor.getTextField();
//            textField.setOpaque(false); // Tắt nền của text field
//            textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Chỉ vẽ đường dưới
//            textField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa nội dung
//        }

        // Thêm JSpinner vào JFrame
        frame.add(spinner);

        // Đảm bảo JFrame hiển thị
        frame.setVisible(true);
    }
}
