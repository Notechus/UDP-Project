package networking;

import java.io.Serializable;

/**
 *
 * @author notechus
 */
public class Packet implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type;
    private String query;

    public Packet() {
        this("no type", "no query");
    }

    public Packet(String type_, String query_) {
        this.type = type_;
        this.query = query_;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Type = " + getType() + " Query = " + getQuery();
    }
}
