package room.demo.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.demo.roomdemo.R
import room.demo.roomdemo.db.UserEntity
import kotlinx.android.synthetic.main.activity_main.*

//Deklarasi kelas dan memperluas
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel
    
// Untuk mengganti metode kelas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         //deklarasi variabel lokal (bisa berupa val atau var)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

// Pada bagian ini untuk memberikan fungsi pada button
        saveButton.setOnClickListener {
            val name  = nameInput.text.toString()
            val email  = emailInput.text.toString()
            val phone = phoneInput.text.toString()
            if(saveButton.text.equals("Save")) {
                val user = UserEntity(0, name, email, phone)
                viewModel.insertUserInfo(user)
            } else {
                val user = UserEntity(nameInput.getTag(nameInput.id).toString().toInt(), name, email, phone)
                viewModel.updateUserInfo(user)
                saveButton.setText("Save")
            }
            nameInput.setText("")
            emailInput.setText("")
        }
    }



    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        nameInput.setText(user.name)
        emailInput.setText(user.email)
        phoneInput.setText(user.phone)
        nameInput.setTag(nameInput.id, user.id)
        saveButton.setText("Update")
    }
}
