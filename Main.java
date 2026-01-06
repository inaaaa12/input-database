import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main extends JFrame {
    JTextField txtNama, txtNPM, txtNoHP;
    JTextArea txtAlamat;
    JRadioButton rbLaki, rbPerempuan;
    JSpinner spinUsia;
    DefaultTableModel model;

    Color navy = new Color(30, 60, 100);
    Color gold = new Color(255, 200, 80);

    public Main() {
        setTitle("Input Data Mahasiswa");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(navy);

        // Judul
        JLabel judul = new JLabel("Input Data Mahasiswa");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setForeground(gold);
        judul.setBounds(30, 20, 400, 30);
        add(judul);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(30, 70, 640, 210);

        label(panel, "Nama:", 20, 15);
        txtNama = input(panel, 120, 15, 220);

        label(panel, "NPM:", 360, 15);
        txtNPM = input(panel, 460, 15, 160);

        label(panel, "Jenis Kelamin:", 20, 55);
        rbLaki = radio(panel, "Laki-laki", 120, 55);
        rbPerempuan = radio(panel, "Perempuan", 230, 55);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbLaki);
        bg.add(rbPerempuan);

        label(panel, "Usia:", 360, 55);
        spinUsia = new JSpinner(new SpinnerNumberModel(17, 1, 100, 1));
        spinUsia.setBounds(460, 55, 160, 28);
        panel.add(spinUsia);

        label(panel, "No. HP:", 20, 95);
        txtNoHP = input(panel, 120, 95, 220);

        label(panel, "Alamat:", 20, 135);
        txtAlamat = new JTextArea();
        txtAlamat.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtAlamat);
        scroll.setBounds(120, 135, 500, 55);
        panel.add(scroll);

        add(panel);

        // Tombol Save
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(550, 290, 120, 38);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBackground(navy);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> save());
        add(btnSave);

        // Table
        String[] coulomn = {"Nama", "NPM", "Jenis Kelamin", "Usia", "No HP", "Alamat"};
        model = new DefaultTableModel(coulomn, 0);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getTableHeader().setBackground(navy);
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBounds(30, 340, 640, 160);
        add(scrollTable);
    }

    void label(JPanel p, String txt, int x, int y) {
        JLabel lbl = new JLabel(txt);
        lbl.setBounds(x, y, 100, 25);
        p.add(lbl);
    }

    JTextField input(JPanel p, int x, int y, int w) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, w, 28);
        p.add(txt);
        return txt;
    }

    JRadioButton radio(JPanel p, String txt, int x, int y) {
        JRadioButton rb = new JRadioButton(txt);
        rb.setBounds(x, y, 100, 25);
        rb.setBackground(Color.WHITE);
        p.add(rb);
        return rb;
    }

    void save() {
        if (txtNama.getText().isEmpty() || txtNPM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan NPM harus diisi!");
            return;
        }

        String jk = rbLaki.isSelected() ? "L" : "P";
        model.addRow(new Object[]{
                txtNama.getText(),
                txtNPM.getText(),
                jk,
                spinUsia.getValue(),
                txtNoHP.getText(),
                txtAlamat.getText()
        });

        txtNama.setText("");
        txtNPM.setText("");
        txtNoHP.setText("");
        txtAlamat.setText("");
        rbLaki.setSelected(false);
        rbPerempuan.setSelected(false);
        spinUsia.setValue(17);

        JOptionPane.showMessageDialog(this, "Data tersimpan!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}