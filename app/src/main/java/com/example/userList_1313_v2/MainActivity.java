package com.example.userList_1313_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userList_1313_v2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;  // RecyclerView — Это "мини-приложение" Android, которое отображает коллекцию элементов в списке или сетке, позволяя пользователю прокручивать коллекцию.
    UserAdapter userAdapter;    // Если нам нужно составить данные, например, в виде массива и скормить его списку (например: RecyclerView) - Адаптер этим и занимается. Он берёт по порядку предоставленные данные и размещает их в списке по порядку. При этом адаптер на лету создаёт нужные компоненты TextView и помещает в него приготовленный текст - или то, что мы (пере)определим в это Методе ниже.
//    ArrayList<String> userList = new ArrayList<>();     // это просто список будущих элементов, что мы хотим позже поместить (в данном случа) в RecyclerView

    Button addUserBtn;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // super <- это значит забираем метод от Родителя
        setContentView(R.layout.activity_main); // указываем где мы храним наш контент (где его описали) - в нашем случае в activity_main.xml
        users = new Users(MainActivity.this);

//        for (int i = 0; i < 100; i++) {
//            userList.add("User #"+i);       // заполняем циклом наш ArrayList текстом - в нашем случае наименованиями User-ов
//        }
        recyclerView = findViewById(R.id.recyclerView);     // "отождествляем" нашу переменную recyclerView (тип "мини-приложение" RecyclerView) - d yfitv ckexft 'nj res\layout\activity_main.xml -> androidx.recyclerview.widget.RecyclerView -> id="@+id/recyclerView"
        addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));      // устанавливаем в каком Layout мы будем наш RecyclerView отображать

    }

    @Override
    public void onResume() {
        super.onResume();
        userAdapter = new UserAdapter(users.getUserList());
//        userAdapter = new UserAdapter(userList);    // создаем новый и "принимаем" в наш Адаптер наш userList - т.е. список из наших User-ов
        recyclerView.setAdapter(userAdapter);   // вызываем метод setAdapter(от нашего userAdapter в нашем "мини-приложении" RecyclerView
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{    // общую логику Holder-а надо бы получше понять..
        TextView itemTextView;
        String userName;
        int userPosition; // нужно для поиска позиции выбранного по клику пользователя в списке пользователей

        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this); // нужно было увязать itemView c Листенером - чтобы эта формочка ждала Клик по ней
        }
        public void bind(String userName, int position){
            this.userName = userName;
            userPosition = position;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
//            String poz = Integer.toString(userPosition);
//            Toast.makeText(MainActivity.this,poz,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
            intent.putExtra("userPosition", userPosition);
            startActivity(intent);
        }
    }

    public class UserAdapter extends RecyclerView.Adapter<UserHolder>{  // Адаптер, содержит данные и увязывает их со Списком
        ArrayList<User> userList = new ArrayList<>();     // ну это уже можно было не определять. Есть выше.

        public UserAdapter(ArrayList<User> userList) {    // Конструктор?? для UserAdapter
            this.userList = userList;
        }

        @Override   // "переопределение" для Родительского Метода
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {      // метод создает "бассейн" из строк, сформированный с помощью onBindViewHolder. То, что отображается в данный момент на активном экране. (https://ziginsider.github.io/RecyclerView/). parrent – родительский ViewGroup-элемент для создаваемого View/ viewType (в нашем случае) - это ID layout-файла, который будет использован для создания View.
            // Еще вариант: onCreateViewHolder() создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом. Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder, и каждый дочерний view-компонент может быть найден и сохранен. (https://devcolibri.com/как-работать-с-recyclerview/)
            // Дополнение: объекты ViewHolder создаются для нескольких первых view-компонентов, а затем они переиспользуются, и адаптер просто привязывает данные при помощи метода onBindViewHolder(). Благодаря этому список очень эффективен, и пользователь может прокручивать список плавно, потому что наиболее тяжёлые операции (создание и поиск элементов view-компонентов) происходят внутри метода onCreateViewHolder()

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);   // LayoutInflater – это класс (метод), который умеет из содержимого layout-файла создать View-элемент. Метод который это делает называется inflate. (https://startandroid.ru/ru/uroki/vse-uroki-spiskom/80-urok-40-layoutinflater-uchimsja-ispolzovat.html)
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {     // метод "перерабатывает" ("формирует") каждую строку для RecyclerView. (ВЕРНО ЛИ? => Важно, строки формируютмя только в момент появлкния их на экране - т.е. в момент прокрутки! До этого они "не существуют" и не занимают память. (https://www.ohandroid.com/onbindviewholder-x.html))
            // Еще вариант: onBindViewHolder() принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте. (https://devcolibri.com/как-работать-с-recyclerview/)
            User user = userList.get(position);
            String userName = user.getUserName()+ " " +user.getUserLastName();
            userHolder.bind(userName, position); // Как правильно сформулировать этот bind?
        }

        @Override
        public int getItemCount() {     // getItemCount() возвращает общее количество элементов списка. Значения списка передаются с помощью Конструктора. (https://devcolibri.com/как-работать-с-recyclerview/)
            return userList.size();
        }
    }
}