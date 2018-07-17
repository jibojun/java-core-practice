package generic;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2017/1/18_12:36 AM
 */
public class Result<T> {
    private long id;
    private T response;

    public Result(long id, T response){
        this.id=id;
        this.response=response;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
