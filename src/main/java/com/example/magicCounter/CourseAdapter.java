package com.example.magicCounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.Viewholder> {

    //private final ArrayList<String> colorList = new ArrayList<String>();

    private Context context;
    private ArrayList<CourseModel> courseModelArrayList;

    ArrayList<Integer> colorList = new ArrayList<Integer>(
            Arrays.asList(R.color.black2, R.color.yellow, R.color.blue, R.color.red, R.color.green));
    // "#FFD1C55F","#FF200923","#FFD30000","#FF186EB3", "#FF2B7C2E"

    // Constructor
    public CourseAdapter(Context context, ArrayList<CourseModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public CourseAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.Viewholder holder, int position) {


        holder.close_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    courseModelArrayList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), courseModelArrayList.size());
                }
                catch(Exception e) {
                    //  do nothing
                }



            }
        });

        holder.close_cross.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(R.drawable.magiclogo)
                        .setTitle("Closing Activity")
                        .setMessage("Are you sure you want to close this activity?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    int size = courseModelArrayList.size();
                                    courseModelArrayList.clear();
                                    notifyItemRangeRemoved(0, size);

                                    Toast toast = Toast.makeText(context, "All entities suppressed", Toast.LENGTH_LONG);
                                    toast.show();
                                }catch(Exception e){
                                    Toast toast = Toast.makeText(context, "Oops something went wrong ;(", Toast.LENGTH_LONG);
                                    toast.show();
                                }





                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

        holder.b_apply.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for (int i = 0; i < courseModelArrayList.size(); i ++) {
                    courseModelArrayList.get(i).applyLife();
                }
                notifyDataSetChanged();
                Toast toast = Toast.makeText(context, "All applied", Toast.LENGTH_LONG);
                toast.show();
                return true;
            }
        });



        // to set data to textview and imageview of each card layout
        CourseModel model = courseModelArrayList.get(position);
        holder.pv.setText(model.getPv());
        holder.change.setText(model.getChange());
        holder.player_name.setText(model.getPlayer_name());
        holder.type_count.setText(model.getType_count());
        int colorInt  = colorList.get(model.getColor()%5);
        holder.layout.setBackgroundResource(colorInt);



        holder.player_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        if(userInput.getText().toString() != null){
                                            holder.player_name.setText(userInput.getText());
                                            model.setPlayer_name(userInput.getText().toString());
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });

        holder.type_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        if(userInput.getText().toString() != null){
                                            holder.type_count.setText(userInput.getText());
                                            model.setType_count(userInput.getText().toString());
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });




        holder.b_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pv_start = holder.change.getText().toString();
                String pv_final = Integer.toString((Integer.parseInt(pv_start)+1));

                if (Integer.parseInt(pv_final)>0) {
                    holder.change.setText("+"+pv_final);
                    model.setChange("+"+pv_final);

                }else{
                    holder.change.setText(pv_final);
                    model.setChange(pv_final);
                }

            }
        });





















        holder.b_plus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 500);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {

                    String pv_start = holder.change.getText().toString();
                    String pv_final = Integer.toString((Integer.parseInt(pv_start)+1));

                    if (Integer.parseInt(pv_final)>0) {
                        holder.change.setText("+"+pv_final);
                        model.setChange("+"+pv_final);

                    }else{
                        holder.change.setText(pv_final);
                        model.setChange(pv_final);
                    }

                    mHandler.postDelayed(this, 70);
                }
            };

        });




        holder.b_minus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 1000);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {

                    String pv_start = holder.change.getText().toString();
                    String pv_final = Integer.toString((Integer.parseInt(pv_start)-1));

                    if (Integer.parseInt(pv_final)>0) {
                        holder.change.setText("+"+pv_final);
                        model.setChange("+"+pv_final);
                    }else{
                        holder.change.setText(pv_final);
                        model.setChange(pv_final);
                    }

                    mHandler.postDelayed(this, 70);
                }
            };

        });

















        holder.b_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pv_start = holder.change.getText().toString();
                String pv_final = Integer.toString((Integer.parseInt(pv_start)-1));
                
                if (Integer.parseInt(pv_final)>0) {
                    holder.change.setText("+"+pv_final);
                    model.setChange("+"+pv_final);
                }else{
                    holder.change.setText(pv_final);
                    model.setChange(pv_final);
                }
            }
        });

        holder.b_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pv_to_add = Integer.parseInt(holder.change.getText().toString());
                int act_pv = Integer.parseInt(holder.pv.getText().toString());
                String pv_final = Integer.toString(pv_to_add + act_pv);
                model.setPv(pv_final);
                model.setChange("0");
                holder.change.setText("0");
                holder.pv.setText(pv_final);
            }
        });

        holder.color_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setColor(model.getColor()+1);
                int colorInt  = colorList.get(model.getColor()%5);
                holder.layout.setBackgroundResource(colorInt);

            }
        });
        //holder.name_input.setText(model.getPlayer_name());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView player_name, type_count, color_choose;
        private TextView pv, change;
        private Button b_plus, b_minus, b_apply, close_cross;
        private ConstraintLayout layout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            pv = itemView.findViewById(R.id.pv);
            //name_input = itemView.findViewById(R.id.name_input);
            change = itemView.findViewById(R.id.change);
            b_plus = itemView.findViewById(R.id.add_b);
            b_minus = itemView.findViewById(R.id.minus_b);
            b_apply = itemView.findViewById(R.id.apply_b);
            close_cross = itemView.findViewById(R.id.close_cross);
            player_name = itemView.findViewById(R.id.player_name);
            layout = itemView.findViewById(R.id.thelayout);
            type_count = itemView.findViewById(R.id.type_count);
            color_choose = itemView.findViewById(R.id.color_choose);
        }
    }
}
