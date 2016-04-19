package id.ols.models;

/**
 * Created by macbook on 4/19/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Act {
    @SerializedName("get")
    @Expose
    private Integer get;
    @SerializedName("insert")
    @Expose
    private Integer insert;
    @SerializedName("update")
    @Expose
    private Integer update;
    @SerializedName("delete")
    @Expose
    private Integer delete;

    /**
     *
     * @return
     * The get
     */
    public Integer getGet() {
        return get;
    }

    /**
     *
     * @param get
     * The get
     */
    public void setGet(Integer get) {
        this.get = get;
    }

    /**
     *
     * @return
     * The insert
     */
    public Integer getInsert() {
        return insert;
    }

    /**
     *
     * @param insert
     * The insert
     */
    public void setInsert(Integer insert) {
        this.insert = insert;
    }

    /**
     *
     * @return
     * The update
     */
    public Integer getUpdate() {
        return update;
    }

    /**
     *
     * @param update
     * The update
     */
    public void setUpdate(Integer update) {
        this.update = update;
    }

    /**
     *
     * @return
     * The delete
     */
    public Integer getDelete() {
        return delete;
    }

    /**
     *
     * @param delete
     * The delete
     */
    public void setDelete(Integer delete) {
        this.delete = delete;
    }


}
