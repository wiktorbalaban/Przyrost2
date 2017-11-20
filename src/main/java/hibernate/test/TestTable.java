package hibernate.test;

import javax.persistence.*;

@Entity
@Table(name = "TestTable", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"table_name"})
})
class TestTable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "cloumn_count")
    private int columnCount;

    public TestTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
