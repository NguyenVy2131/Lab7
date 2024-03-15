/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cdstore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.Border;

/**
 *
 * @author LENOVO
 */
public class CDStoreFrame extends javax.swing.JDialog {

    private JTextField idField, titleField, priceField, yearOfReleaseField;
    private JButton addButton, clearButton, showAllButton;
    private JComboBox collection;
    private JRadioButton rb1, rb2;
    private ButtonGroup group;
    private CDdetail cd;

    public CDStoreFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //init form

        setTitle("Quach Tinh_CD Store");
        setSize(500, 500);
        // setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        //Layout control
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        JPanel type = new JPanel(new GridLayout(1, 3, 5, 5));
        JPanel collect = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel buttons = new JPanel(new GridLayout(1, 3, 5, 5));
        String obj[] = {"Movie", "MV", "Record"};
        collection = new JComboBox(obj);
        idField = new JTextField();
        titleField = new JTextField();
        priceField = new JTextField();
        yearOfReleaseField = new JTextField();
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        showAllButton = new JButton("Show All");
        group = new ButtonGroup();
        rb1 = new JRadioButton("VCD");
        rb2 = new JRadioButton("CD");
        
        rb1.setBounds(75, 50, 100, 30);
        rb2.setBounds(75, 100, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        group.add(rb1);
        group.add(rb2);
        //Add control to Layout(type Layout)
        type.add(new JLabel("CD Type "));
        type.add(rb1);
        type.add(rb2);
        //Add control to Layout(collection Layout)
        collect.add(new JLabel("CD Collection "));
        collect.add(collection);
        //Add control to Layout(inputPanel Layout)
        inputPanel.add(new JLabel("CD ID "));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("CD Title "));
        inputPanel.add(titleField);
        inputPanel.add(collect);
        inputPanel.add(type);
        inputPanel.add(new JLabel("CD Price "));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("CD Year of Release "));
        inputPanel.add(yearOfReleaseField);
        //Add control to Layout(buttons Layout)
        buttons.add(addButton);
        buttons.add(clearButton);
        buttons.add(showAllButton);
        //Add imputPanel layout to mainpanel Layout
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttons, BorderLayout.SOUTH);
        add(mainPanel);

        //mainPanel.add(displayButton, BorderLayout.EAST);
        //addButton.addActionListener();
        addButton.addActionListener((ActionEvent e) -> {
            addCD();
        });
        clearButton.addActionListener((ActionEvent e) -> {
            resetField();
        });
        showAllButton.addActionListener((ActionEvent e) -> {
            dispose();
            
        });
        setVisible(true);
    }

    public CDdetail getCd() {
        return cd;
    }

    private void addCD() {
        if (idField.getText().isEmpty()) {
            setColorBorder(idField, Color.RED);
//            JOptionPane.showMessageDialog(this, "Please enter the CD id");
            idField.requestFocus();
        } else if (titleField.getText().isEmpty()) {
            setColorBorder(titleField, Color.RED);
            JOptionPane.showMessageDialog(this, "Please enter the CD Title");
            titleField.requestFocus();
        } else if (priceField.getText().isEmpty()) {
            setColorBorder(priceField, Color.RED);
            JOptionPane.showMessageDialog(this, "Please enter the CD Price");
            priceField.requestFocus();
        } else if (yearOfReleaseField.getText().isEmpty()) {
            setColorBorder(yearOfReleaseField, Color.RED);
            JOptionPane.showMessageDialog(this, "Please enter the CD year of release");
            yearOfReleaseField.requestFocus();
        } else if (!isNumber(priceField.getText())) {
            setColorBorder(priceField, Color.RED);
            JOptionPane.showMessageDialog(this, "Invalid Number: " + priceField.getText());
            priceField.requestFocus();
        } else if (!isNumber(yearOfReleaseField.getText())) {
            setColorBorder(yearOfReleaseField, Color.RED);
            JOptionPane.showMessageDialog(this, "Invalid Number: " + yearOfReleaseField.getText());

            yearOfReleaseField.requestFocus();
        } else {
            String id = idField.getText();
            String title = titleField.getText();
            String collection = (String) this.collection.getItemAt(this.collection.getSelectedIndex());
            String type = "";
            if (rb1.isSelected()) {
                type = "VCD";
            }
            if (rb2.isSelected()) {
                type = "CD";
            }
            Double price = Double.valueOf(priceField.getText());
            Integer year = Integer.valueOf(yearOfReleaseField.getText());
            cd = new CDdetail(id, collection, type, title, price, year);
            clearFields();
            JOptionPane.showMessageDialog(this, "Successful!");
            resetField();
            //setVisible(false);
        }
    }
    public void resetField() {
        idField.setText("");
        setColorBorder(idField, Color.BLACK);
        titleField.setText("");
        setColorBorder(titleField, Color.BLACK);
        collection.setSelectedIndex(-1);
        priceField.setText("");
        setColorBorder(priceField, Color.BLACK);
        yearOfReleaseField.setText("");
        setColorBorder(yearOfReleaseField, Color.BLACK);
        group.clearSelection();
    }

    public boolean isNumber(String a) {
        try {
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        priceField.setText("");
        yearOfReleaseField.setText("");
    }

    public void setColorBorder(JTextField textField, Color color) {
        Border newBorder = BorderFactory.createLineBorder(color);
        textField.setBorder(newBorder);
    }
    public void displayAll(ArrayList<CDdetail> list) {
        JFrame CDlist = new JFrame();
        CDlist.setSize(300, 200);
        CDlist.setTitle("All CDs");
        CDlist.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        CDlist.setLocationRelativeTo(null);
        StringBuilder sb = new StringBuilder();
        for (CDdetail cd : list) {
            sb.append(cd.getId() + " ").append("_" + cd.getTitle() + " ").append("_" + cd.getCollection() + " ").append("_" + cd.getType() + " ")
                    .append("_" + cd.getPrice() + "$ ").append("_" + cd.getYear() + "\n");
        }
        JTextArea area = new JTextArea(sb.toString());
        CDlist.add(area);
        CDlist.pack();
        CDlist.setVisible(true);
    }

}
