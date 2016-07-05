package com.couse.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.couse.schedule.model.Schedule;

public class CustomTableModel extends DefaultTableModel {

    private List<Schedule> schedule;

    public CustomTableModel() {
        schedule = new ArrayList<>();
    }

    public void addSchedule(Schedule Schedule) {
        schedule.add(Schedule);
        fireTableRowsInserted(schedule.size() - 1, schedule.size() - 1);
    }

    public Schedule getScheduleAt(int row) {
        return schedule.get(row);
    }

    public List<Schedule> getChangedschedule() {
        List<Schedule> changed = new ArrayList<>(schedule.size());

        for (Schedule p : schedule) {
            if (p.hasChanged()) {
                changed.add(p);
            }
        }

        return changed;    
    }

    @Override
    public int getRowCount() {
        return schedule.size();
    }

    @Override
    public String getColumnName(int column) {
        String name = null;
        switch (column) {
            case 0:
                name = "First name";
                break;
            case 1:
                name = "First name";
                break;
        }
        return name;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Schedule p = schedule.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = p.getFaculty();
                break;
            case 1:
                value = p.getCredits();
                break;
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue instanceof String) {
            Schedule p = schedule.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    p.setFaculty(aValue.toString());
                    break;
                case 1:
                    p.setCredits(aValue.toString());
                    break;
            }
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }
}