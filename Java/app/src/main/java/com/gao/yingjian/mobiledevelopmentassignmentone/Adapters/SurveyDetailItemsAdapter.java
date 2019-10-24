package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.SurveyDetailItemInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.*;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.Services.SurveyDataManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class SurveyDetailItemsAdapter extends BaseAdapter
        implements CompoundButton.OnCheckedChangeListener,
            TextView.OnEditorActionListener,
            View.OnFocusChangeListener{
    private LayoutInflater layoutInflater;
    private Context context;

    private String surveyId;
    private ArrayList<SurveyDetailItem> surveyDetailItems;
    private Hashtable<Integer, Object> valueTable;

    public SurveyDetailItemsAdapter(LayoutInflater layoutInflater, Context context, String surveyId, ArrayList<SurveyDetailItem> surveyDetailItems) {
        super();
        this.valueTable = new Hashtable<>();
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.surveyId = surveyId;
        this.surveyDetailItems = surveyDetailItems;
        if(surveyDetailItems.size() > 0) {
            ArrayList<SurveyDetailItemInfo> items = SurveyDataManager.getInstance().getSurveyDetailItemData(this.surveyId);
            for (SurveyDetailItemInfo info : items) {
                SurveyDetailItem item = getSurveyDetailItem(surveyDetailItems, info);
                if(item != null){
                    Object obj = info.getValue();
                    if(item.getType() == SurveyDetailItem.InputType.CheckBox){
                        String[] values = info.getValue().split("\\$");
                        HashSet<String> set = new HashSet<>();
                        for(String v : values){
                            set.add(v);
                        }
                        obj = set;
                    }
                    this.valueTable.put(info.getItemId(), obj);
                }
            }
        }
    }

    @Override
    public int getCount() {
        return surveyDetailItems.size();
    }

    @Override
    public Object getItem(int i) {
        return surveyDetailItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") LinearLayout item = (LinearLayout)this.layoutInflater.inflate(R.layout.survey_detail_item, null);
        TextView txvSurveyDetailItemTitle = item.findViewById(R.id.txvSurveyDetailItemTitle);
        EditText edtInputText = item.findViewById(R.id.edtInputText);
        LinearLayout lltCheckBoxList = item.findViewById(R.id.lltCheckBoxList);
        RadioGroup rdgRadioGroup = item.findViewById(R.id.rdgRadioGroup);
        SurveyDetailItem detailItem = (SurveyDetailItem)getItem(i);

        txvSurveyDetailItemTitle.setText(detailItem.getTitle());
        Object value = this.valueTable.getOrDefault(detailItem.getItemId(), null);
        switch (detailItem.getType()){
            case Text:
                edtInputText.setVisibility(View.VISIBLE);
                edtInputText.setTag(detailItem);
                if(value != null){
                    edtInputText.setText(value.toString());
                }
                edtInputText.setOnEditorActionListener(this);
                edtInputText.setOnFocusChangeListener(this);
                break;
            case CheckBox:
                lltCheckBoxList.setVisibility(View.VISIBLE);
                if(value == null){
                    value = new HashSet<String>();
                    this.valueTable.put(detailItem.getItemId(), value);
                }
                initialCheckBoxList(lltCheckBoxList, detailItem);
                break;
            case RadioButton:
                rdgRadioGroup.setVisibility(View.VISIBLE);
                initialRadioButtonList(rdgRadioGroup, detailItem);
                break;
            default:
                break;
        }
        return item;
    }

    private void initialCheckBoxList(LinearLayout container, SurveyDetailItem detailItem) {
        HashSet<String> values = (HashSet<String>)this.valueTable.getOrDefault(detailItem.getItemId(), null);
        for (String item : detailItem.getValues()){
            CheckBox checkBox = new CheckBox(this.context);
            if(values != null && values.contains(item)){
                checkBox.setChecked(true);
            }
            checkBox.setText(item);
            checkBox.setTag(detailItem);
            checkBox.setOnCheckedChangeListener(this);
            container.addView(checkBox);
        }
    }

    private void initialRadioButtonList(RadioGroup container, SurveyDetailItem detailItem) {
        Object value = this.valueTable.getOrDefault(detailItem.getItemId(), null);
        for (String item : detailItem.getValues()){
            RadioButton radioButton = new RadioButton(this.context);
            radioButton.setText(item);
            radioButton.setTag(detailItem);
            radioButton.setOnCheckedChangeListener(this);
            container.addView(radioButton);
            if(value != null && value.toString().equals(item)){
                container.check(radioButton.getId());
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        SurveyDetailItem detailItem = (SurveyDetailItem) compoundButton.getTag();
        if(compoundButton instanceof CheckBox) {
            String value = updateValues(detailItem.getItemId(), compoundButton.getText().toString(), checked);
            SurveyDataManager.getInstance().updateSurveyDetailItemData(this.surveyId, detailItem.getItemId(), value);
        }else if(compoundButton instanceof RadioButton){
            if(checked){
                this.valueTable.put(detailItem.getItemId(), compoundButton.getText().toString());
                SurveyDataManager.getInstance().updateSurveyDetailItemData(this.surveyId, detailItem.getItemId(), compoundButton.getText().toString());
            }
        }
    }

    private String updateValues(Integer id, String option, boolean checked) {
        HashSet<String> values = (HashSet<String>)this.valueTable.get(id);
        if(values.contains(option) && !checked){
            values.remove(option);
        }else if(!values.contains(option) && checked){
            values.add(option);
        }
        return String.join("$", values);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (isDoneEditing(actionId, event)) {
            if (event == null || !event.isShiftPressed()) {
                saveTextData(textView);
                return false; // consume.
            }
        }
        return true; // pass on to other listeners.
    }

    @Override
    public void onFocusChange(View view, boolean focused) {
        if(!focused && view instanceof TextView){
            saveTextData((TextView)view);
        }
    }

    private void saveTextData(TextView textView) {
        SurveyDetailItem detailItem = (SurveyDetailItem) textView.getTag();
        this.valueTable.put(detailItem.getItemId(), textView.getText().toString());
        // the user is done typing.
        SurveyDataManager.getInstance().updateSurveyDetailItemData(this.surveyId, detailItem.getItemId(), textView.getText().toString());
    }

    private boolean isDoneEditing(int actionId, KeyEvent event) {
        return actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
    }

    private SurveyDetailItem getSurveyDetailItem(ArrayList<SurveyDetailItem> surveyDetailItems, SurveyDetailItemInfo info) {
        for(SurveyDetailItem item : surveyDetailItems){
            if(item.getItemId() == info.getItemId()){
                return item;
            }
        }
        return null;
    }
}
