package com.learn_thing.learnthingandroid.Activity.Adapters;

import com.learn_thing.learnthingandroid.Activity.MainActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
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
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Andrew on 23.07.2015.
 */
public class SubjectCardAdapter extends RecyclerView.Adapter<SubjectCardAdapter.PersonViewHolder> {
    private View view = null;

    private Context context = null;

    public void setContext(Context context) {
        this.context = context;
    }

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

    public SubjectCardAdapter(List<SubjectCard> data, Context context) {
        this.data = data;
        this.context = context;
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
    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int i) {
        final AlarmReciever alarmReciever = new AlarmReciever();
        IntentFilter intentFilter = new IntentFilter("NotificationSubject");
        context.registerReceiver(alarmReciever, intentFilter);
        final int index = i;
        final SubjectCard objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getName());
        personViewHolder.statusValue.setText(objectItem.getStatus());
        personViewHolder.checkBox.setChecked(objectItem.isReminder());
        personViewHolder.checkBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            SubjectDB subjectDB = new SubjectDB(context);
                            subjectDB.getRealm().beginTransaction();
                            data.get(index).setIsReminder(true);
                            subjectDB.getRealm().commitTransaction();
                            Intent intent = new Intent();
                            intent.setAction("NotificationSubject");
                            intent.putExtra("title", objectItem.getName());
                            intent.putExtra("text", "Напоминание. У вас есть незаконченые предметы!");
                            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
                            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000*24, pIntent);
                        } else {
                            SubjectDB subjectDB = new SubjectDB(context);
                            subjectDB.getRealm().beginTransaction();
                            data.get(index).setIsReminder(false);
                            subjectDB.getRealm().commitTransaction();
                        }
                    }
                }

        );
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
                                                        }

        );

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



