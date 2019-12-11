package com.example.tdingb51a04;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private ArrayList<Integer> classIds;
    private ArrayList<Integer> levelIds;
    private ArrayList<Integer> sourceIds;
    private ArrayList<Integer> schoolIds;
    private ArrayList<Integer> effectIds;
    private ArrayList<Integer> filterOnConcentrations;
    private ArrayList<Integer> filterOnRituals;
    //    boolean filterOnConcentration;
//    boolean filterOnRitual;
    private boolean filterOnVerbal;
    private boolean filterOnSomatic;
    private boolean filterOnMaterial;
    private TextView lblnumber;


    public FilterListAdapter(Context context, List<String> listDataHeader,
                             HashMap<String, List<String>> listChildData,
                             ArrayList<Integer> classIds, ArrayList<Integer> levelIds,
                             ArrayList<Integer> sourceIds, ArrayList<Integer> schoolIds,
                             ArrayList<Integer> effectIds,
                             ArrayList<Integer> filterOnConcentrations,
                             ArrayList<Integer> filterOnRituals,
                             boolean filterOnVerbal, boolean filterOnSomatic,
                             boolean filterOnMaterial) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.classIds = classIds;
        this.levelIds = levelIds;
        this.sourceIds = sourceIds;
        this.schoolIds = schoolIds;
        this.effectIds = effectIds;
        this.filterOnConcentrations = filterOnConcentrations;
        this.filterOnRituals = filterOnRituals;
        this.filterOnVerbal = filterOnVerbal;
        this.filterOnSomatic = filterOnSomatic;
        this.filterOnMaterial = filterOnMaterial;

    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        // The child views in each row.
        CheckBox checkBox;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        txtListChild.setText(childText);
        switch (groupPosition){
            case 0:
                checkBox.setChecked(classIds.contains(childPosition));
                lblnumber.setText(classIds.size()+"");
                break;
            case 1:
                checkBox.setChecked(levelIds.contains(childPosition));
                lblnumber.setText(levelIds.size()+"");
                break;
            case 2:
                checkBox.setChecked(sourceIds.contains(childPosition));
                lblnumber.setText(sourceIds.size()+"");
                break;
            case 3:
                checkBox.setChecked(schoolIds.contains(childPosition));
                lblnumber.setText(schoolIds.size()+"");
                break;
            case 4:
                int value;
                if(childPosition == 0)
                    value = 1;
                else
                    value = 0;
                checkBox.setChecked(filterOnConcentrations.contains(value));
                lblnumber.setText(filterOnConcentrations.size()+"");
                break;
            case 5:
                int valuerit;
                if(childPosition == 0)
                    valuerit = 1;
                else
                    valuerit = 0;
                checkBox.setChecked(filterOnRituals.contains(valuerit));
                lblnumber.setText(filterOnRituals.size()+"");
                break;
            case 6:
                switch (childPosition){
                    case 0:
                        checkBox.setChecked(filterOnVerbal);
                        break;
                    case 1:
                        checkBox.setChecked(filterOnSomatic);
                        break;
                    case 2:
                        checkBox.setChecked(filterOnMaterial);
                        break;
                }
                int num = 0;
                if(filterOnVerbal)
                    num++;
                if(filterOnSomatic)
                    num++;
                if(filterOnMaterial)
                    num++;
                lblnumber.setText(num+"");
                break;
            case 7:
                checkBox.setChecked(effectIds.contains(childPosition));
                lblnumber.setText(effectIds.size()+"");
                break;
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        TextView lblNum = (TextView)convertView.findViewById(R.id.txtNumSelected);
        lblnumber = lblNum;
        switch (groupPosition){
            case 0:
                lblNum.setText(classIds.size()+"");
                break;
            case 1:
                lblNum.setText(levelIds.size()+"");
                break;
            case 2:
                lblNum.setText(sourceIds.size()+"");
                break;
            case 3:
                lblNum.setText(schoolIds.size()+"");
                break;
            case 4:
                lblNum.setText(filterOnConcentrations.size()+"");
                break;
            case 5:
                lblNum.setText(filterOnRituals.size()+"");
                break;
            case 6:
                int num = 0;
                if(filterOnVerbal)
                    num++;
                if(filterOnSomatic)
                    num++;
                if(filterOnMaterial)
                    num++;
                lblNum.setText(num+"");
                break;
            case 7:
                lblNum.setText(effectIds.size()+"");
                break;
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
