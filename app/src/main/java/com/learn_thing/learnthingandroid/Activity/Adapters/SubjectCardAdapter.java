package com.learn_thing.learnthingandroid.Activity.Adapters;
import com.learn_thing.learnthingandroid.Activity.MainActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 23.07.2015.
 */
public class SubjectCardAdapter extends RecyclerView.Adapter<SubjectCardAdapter.PersonViewHolder> {
    private View view = null;

    public void setData(List<SubjectCard> data) {
        this.data = data;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    Activity activity = null;
    List<SubjectCard> data;
    SubjectCardAdapter adapter = this;

    public List<SubjectCard> getData() {
        return data;
    }

    public void addData(ArrayList<SubjectCard> arrayList) {
        for (SubjectCard subjectCard : arrayList) {
            data.add(subjectCard);
        }
    }

    public SubjectCardAdapter(List<SubjectCard> data) {
        this.data = data;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.predmet_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        view = v;
        return pvh;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        SubjectCard objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getName());
        personViewHolder.statusValue.setText(objectItem.getStatus());
        personViewHolder.checkBox.setChecked(false);
        personViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             /*   if (isChecked) {
                    Intent myIntent = new Intent(activity, MainActivity.class);
                    @SuppressLint("ServiceCast") AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
                    PendingIntent pendingIntent = PendingIntent.getService(activity, 0, myIntent, 0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 56);
                    calendar.set(Calendar.SECOND, 00);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);  //set repeating every 24 hours
                }*/
            }
        });
        personViewHolder.motivation.setText(objectItem.getMotivation());
        personViewHolder.imageView.setImageResource(MainActivity.img[objectItem.getImg()]);
        personViewHolder.closeButton.setOnClickListener(new View.OnClickListener() {

            public void showAlert() {
                AlertDialog.Builder ad = new AlertDialog.Builder(personViewHolder.itemView.getContext());
                ad.setTitle("Увага");  // заголовок
                ad.setMessage("Ви впевнені, що хочете видалити"); // сообщение
                ad.setPositiveButton("Так", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        int position = personViewHolder.getAdapterPosition();
                        SubjectDB subjectDB = new SubjectDB(personViewHolder.itemView.getContext());
                        subjectDB.deleteById(data.get(position).getId());
                        adapter.setData(subjectDB.getAllRealmResultSubjects());
                        adapter.notifyDataSetChanged();
                        MainActivity.checkEmpty();

                    }
                });
                ad.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        return;
                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        return;
                    }
                }).show();
            }

            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

    }

    public void onG(View v) {

    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView statusValue;
        Switch checkBox = null;
        ImageView imageView = null;
        ImageButton closeButton = null;
        TextView motivation = null;

        PersonViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.headerCard);
            statusValue = (TextView) itemView.findViewById(R.id.statusValue);
            checkBox = (Switch) itemView.findViewById(R.id.switchBox);
            imageView = (ImageView) itemView.findViewById(R.id.imageTechnic);
            closeButton = (ImageButton) itemView.findViewById(R.id.deleteCardButton);
            motivation = (TextView) itemView.findViewById(R.id.motivationValue);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}



