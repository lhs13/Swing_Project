package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.model.Recipe;
import com.model.RecipeDBA;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class RecipeView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField tfName;
	private JTextField tfStuff;
	private JScrollPane scrollPane_3;
	private JTextArea taRecipe;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNewLabel_7;
	private JComboBox cbSearch;
	private JTextField tfSearch;
	private JButton btnSearch;
	RecipeDBA dba = new RecipeDBA();
	private JTextField tfNum;
	private JButton btnNew;
	private JLabel lblNewLabel_1;
	private JTextField tfKind;
	ArrayList<Recipe> rec;
	int row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeView frame = new RecipeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RecipeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getTabbedPane(), BorderLayout.CENTER);
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBackground(new Color(255, 250, 250));
			tabbedPane.setName("");
			tabbedPane.addTab("레시피목록", null, getPanel(), null);
			tabbedPane.addTab("레시피보기", null, getPanel_1(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(240, 255, 255));
			panel.setLayout(null);
			panel.add(getScrollPane());
			panel.add(getLblNewLabel());
			panel.add(getBtnNew());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(248, 248, 255));
			panel_1.setLayout(null);
			panel_1.add(getLblNewLabel_3());
			panel_1.add(getLblNewLabel_5());
			panel_1.add(getLblNewLabel_6());
			panel_1.add(getTfName());
			panel_1.add(getTfStuff());
			panel_1.add(getScrollPane_3());
			panel_1.add(getBtnInsert());
			panel_1.add(getBtnUpdate());
			panel_1.add(getBtnDelete());
			panel_1.add(getLblNewLabel_7());
			panel_1.add(getCb());
			panel_1.add(getTfSearch());
			panel_1.add(getBtnSearch());
			panel_1.add(getTfNum());
			panel_1.add(getLblNewLabel_1());
			panel_1.add(getTfKind());
		}
		return panel_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			scrollPane.setBackground(new Color(224, 255, 255));
			scrollPane.setBounds(91, 75, 662, 316);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setSelectionBackground(new Color(135, 206, 250));
			table.setForeground(new Color(0, 0, 0));
			table.setGridColor(new Color(135, 206, 235));
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
			table.setBackground(new Color(255, 255, 255));
			table.setSize(new Dimension(5, 50));
			table.setRowHeight(30);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					tfNum.setText("");
					tfName.setText("");
					tfKind.setText("");
					tfStuff.setText("");
					tfSearch.setText("");
					taRecipe.setText("");
					
					int i = table.getSelectedRow();
					tfNum.setText(rec.get(i).getNum()+"");
					tfName.setText(rec.get(i).getCookname());
					tfKind.setText(rec.get(i).getCookkind());
					tfStuff.setText(rec.get(i).getCookstuff());
					taRecipe.setText(rec.get(i).getCookrecipe());
					
					row = rec.get(i).getNum();
				}
			});
			String[] cols = {"종류","요리이름","요리 재료"};
			DefaultTableModel dt = new DefaultTableModel(cols,0);
			table.setModel(dt);
		}
		return table;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("레시피 목록");
			lblNewLabel.setForeground(new Color(25, 25, 112));
			lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			lblNewLabel.setBounds(344, 25, 163, 39);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("이름");
			lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(33, 34, 57, 15);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("재료");
			lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_5.setBounds(33, 90, 57, 15);
		}
		return lblNewLabel_5;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("레시피");
			lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_6.setBounds(33, 118, 57, 15);
		}
		return lblNewLabel_6;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(66, 31, 116, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JTextField getTfStuff() {
		if (tfStuff == null) {
			tfStuff = new JTextField();
			tfStuff.setBounds(66, 87, 506, 21);
			tfStuff.setColumns(10);
		}
		return tfStuff;
	}
	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(32, 143, 540, 256);
			scrollPane_3.setViewportView(getTaRecipe());
		}
		return scrollPane_3;
	}
	private JTextArea getTaRecipe() {
		if (taRecipe == null) {
			taRecipe = new JTextArea();
		}
		return taRecipe;
	}
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("레시피추가");
			btnInsert.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			btnInsert.setBackground(new Color(204, 204, 255));
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Recipe r = new Recipe();
					r.setCookname(tfName.getText());
					r.setCookkind(tfKind.getText());
					r.setCookstuff(tfStuff.getText());
					r.setCookrecipe(taRecipe.getText());
					dba.cookInsert(r);
				}
			});
			btnInsert.setBounds(656, 187, 159, 64);
		}
		return btnInsert;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("레시피수정");
			btnUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			btnUpdate.setBackground(new Color(204, 204, 255));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Recipe r = new Recipe();
					r.setNum(Integer.parseInt(tfNum.getText()));
					r.setCookname(tfName.getText());
					r.setCookkind(tfKind.getText());
					r.setCookstuff(tfStuff.getText());
					r.setCookrecipe(taRecipe.getText());
					dba.cookUpdate(r);
				}
			});
			btnUpdate.setBounds(656, 261, 159, 64);
		}
		return btnUpdate;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("레시피삭제");
			btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			btnDelete.setBackground(new Color(204, 204, 255));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int flag = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION);
					if(flag==JOptionPane.OK_OPTION) {
						dba.cookDelete(row);
						tfNum.setText("");
						tfKind.setText("");
						tfName.setText("");
						tfStuff.setText("");
						taRecipe.setText("");
					}
				}
			});
			btnDelete.setBounds(656, 335, 159, 64);
		}
		return btnDelete;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("▶검색◀");
			lblNewLabel_7.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			lblNewLabel_7.setBounds(243, 20, 65, 29);
		}
		return lblNewLabel_7;
	}
	private JComboBox getCb() {
		if (cbSearch == null) {
			cbSearch = new JComboBox();
			cbSearch.setForeground(new Color(102, 0, 102));
			cbSearch.setBackground(new Color(255, 255, 255));
			cbSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			cbSearch.setModel(new DefaultComboBoxModel(new String[] {"-- 구분 --", "요리이름", "대표재료"}));
			cbSearch.setBounds(243, 47, 78, 21);
		}
		return cbSearch;
	}
	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(333, 48, 162, 21);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.setBackground(new Color(204, 204, 255));
			btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String kind = "";
					if(cbSearch.getSelectedIndex()==1) {
						kind = "cookname";
					}else if(cbSearch.getSelectedIndex()==2) {
						kind = "cookstuff";
					}else if(cbSearch.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "검색 구분을 선택하세요!");
					}
					if(tfSearch.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "검색어를 입력하세요!");
					}
					String word = tfSearch.getText();
					ArrayList<Recipe> arr = dba.cookSearch(kind, word);
					tfName.setText("");
					tfStuff.setText("");
					taRecipe.setText("");
					for(Recipe r : arr) {
						tfNum.setText(r.getNum()+"");
						tfName.setText(r.getCookname());
						tfKind.setText(r.getCookkind());
						tfStuff.setText(r.getCookstuff());
						taRecipe.setText(r.getCookrecipe());
					}
				}
			});
			btnSearch.setBounds(507, 46, 65, 23);
		}
		return btnSearch;
	}
	private JTextField getTfNum() {
		if (tfNum == null) {
			tfNum = new JTextField();
			tfNum.setEditable(false);
			tfNum.setBounds(76, 115, 28, 21);
			tfNum.setColumns(10);
		}
		return tfNum;
	}
	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton("F5");
			btnNew.setFont(new Font("Lucida Sans", Font.BOLD, 16));
			btnNew.setForeground(new Color(0, 0, 139));
			btnNew.setBackground(new Color(135, 206, 250));
			btnNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					rec = dba.cookView();
					String[] cols = {"종류","요리이름","요리 재료"};
					DefaultTableModel dt = new DefaultTableModel(cols,rec.size());
					table.setModel(dt);
					for(int i=0; i<rec.size(); i++) {
						table.setValueAt(rec.get(i).getCookkind(), i, 0);
						table.setValueAt(rec.get(i).getCookname(), i, 1);
						table.setValueAt(rec.get(i).getCookstuff(), i, 2);
					}
				}
			});
			btnNew.setBounds(12, 10, 63, 44);
		}
		return btnNew;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("종류");
			lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(33, 62, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfKind() {
		if (tfKind == null) {
			tfKind = new JTextField();
			tfKind.setBounds(66, 59, 116, 21);
			tfKind.setColumns(10);
		}
		return tfKind;
	}
}
