package fekri.com.ux

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class TodoDao {

    @Insert()
    abstract fun insertTask(todoModel: TodoModel):Long

    @Query("Select * from todo_model_table where isFinished == 0")
    abstract fun getTask(): LiveData<List<TodoModel>>

    @Query("Update todo_model_table Set isFinished = 1 where id=:uid")
    abstract fun finishTask(uid:Long)

    @Query("Delete from todo_model_table where id=:uid")
    abstract fun deleteTask(uid:Long)

}