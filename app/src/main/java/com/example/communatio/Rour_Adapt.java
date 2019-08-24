package com.example.communatio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.List;

class Rour_Adapt extends BaseExpandableListAdapter {
    Context context;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;
    public Rour_Adapt(Context context,List<String> listGroup,HashMap<String,List<String>> listItem){
        this.context=context;
        this.listGroup=listGroup;
        this.listItem=listItem;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listItem.get(this.listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listItem.get(this.listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group =(String) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R .layout.rour_list_group,null);

        }
        TextView textView=convertView.findViewById(R.id.list_parent);
        textView.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child =(String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R .layout.rour_list_item,null);

        }
        final TextView textView=convertView.findViewById(R.id.list_child);
        textView.setText(child);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(textView.getText().toString().equals("National Institute of Technology Rourkela (NITR), formerly Regional Engineering College Rourkela (REC Rourkela), is a publicly funded premier institute of higher learning for engineering, science and technology located in the steel city of Rourkela, Odisha, India. It is one of the 31 National Institutes of Technology in India and has been recognized as an Institute of National Importance by the National Institutes of Technology Act, 2007. It is ranked 16 in the NIRF Rankings 2019 of Indian engineering universities."))) {
                    if(textView.getText().toString().equals("CLARION")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://clarionnitr.wordpress.com/"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("E-CELL")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://ecell.nitrkl.ac.in/initiative.html"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("ASME")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.facebook.com/asme.nitrkl/"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("AICHE")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.facebook.com/AIChENITR/"));
                        context.startActivity(browserIntent);
                    }

                    if(textView.getText().toString().equals("Vikram Sarabhai Hall of Residence")){
                        Toast.makeText(context, "Single Seater Male", Toast.LENGTH_SHORT).show();
                    }
                    if(textView.getText().toString().equals("Kiran Majumdar Shaw Hall of Residence")){
                        Toast.makeText(context, "Single & Multi-Seater Female", Toast.LENGTH_SHORT).show();
                    }
                    if(textView.getText().toString().equals("M.Visweswaraya Hall of Residence")){
                        Toast.makeText(context, "Single Seater Male", Toast.LENGTH_SHORT).show();
                    }
                    if(textView.getText().toString().equals("C. V. Raman Hall of Residence")){
                        Toast.makeText(context, "Single-Seater Female", Toast.LENGTH_SHORT).show();
                    }
                    if(textView.getText().toString().equals("Curriculum")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://nitrkl.ac.in/Academics/AcademicProcess/Curricula.aspx"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("Sulekha")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.sulekha.com"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("Practo")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.practo.com"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("TaxiForSure")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.crunchbase.com/organization/taxiforsure-com"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("Storypick")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.storypick.com"));
                        context.startActivity(browserIntent);
                    }
                    if(textView.getText().toString().equals("Mobile:-6389985623")){
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel: +916389985623"));
                        context.startActivity(callIntent);
                    }
                    if(textView.getText().toString().equals("Email:-pradeep@nitr.ac.in")){
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL,  new String[]{"pradeep@nitr.ac.in"});
                        context.startActivity(Intent.createChooser(intent, "Send Email"));
                    }
                    if(textView.getText().toString().equals("Map")){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/maps/search/Nit+rourkela/@22.2524939,84.8997305,16.74z"));
                        context.startActivity(browserIntent);
                    }


                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
