package clue;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

    private boolean DEBUG = false;
    
    private String[] columnNames = {"Cartas",
        "Tipo",
        "J1",
        "J2",
        "J3", "J4"};
    private Object[][] data = {
        {"Sra.Azulino", "Sospechoso", false, false, false, false},
        {"Prf.Moradillo", "Sospechoso", false, false, false, false},
        {"Sra.Blanco", "Sospechoso", false, false, false, false},
        {"Ent.Mostaza", "Sospechoso", false, false, false, false},
        {"Srita.Escarlata", "Sospechoso", false, false, false, false},
        {"Sr.Verdi", "Sospechoso", false, false, false, false},
        {"Bate", "Arma", false, false, false, false},
        {"Cuchillo", "Arma", false, false, false, false},
        {"Pistola", "Arma", false, false, false, false},
        {"Veneno", "Arma", false, false, false, false},
        {"Hacha", "Arma", false, false, false, false},
        {"Pesas", "Arma", false, false, false, false},
        {"Candelabro", "Arma", false, false, false, false},
        {"Cuerda", "Arma", false, false, false, false},
        {"Trofeo", "Arma", false, false, false, false},
        {"Patio", "Habitación", false, false, false, false},
        {"Observatorio", "Habitación", false, false, false, false},
        {"Vestibulo", "Habitación", false, false, false, false},
        {"Hab.Huespedes", "Habitación", false, false, false, false},
        {"Comedor", "Habitación", false, false, false, false},
        {"Teatro", "Habitación", false, false, false, false},
        {"Cocina", "Habitación", false, false, false, false},
        {"Spa", "Habitación", false, false, false, false},
        {"Sala", "Habitación", false, false, false, false}
    };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

    }
}
