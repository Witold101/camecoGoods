package by.vistar.comeco.dao;

public class DaoConstants {
    //----------------------------------- init tables -----------------------------------------------
    public static final String TABLE_NAME_GOOD = "goods";
    public static final String TABLE_NAME_UNIT_OF_MEASURE = "units";
    private static final String MAX_NAME = "50";
    private static final String MAX_FULL_NAME = "150";

    static final String MYSQL_NEW_TABLE_GOOOD =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_GOOD + "` ( `id` INT(11) " +
                    "NOT NULL AUTO_INCREMENT,`name` VARCHAR(" + MAX_NAME + ") NOT NULL," +
                    "`full_name` VARCHAR(" + MAX_FULL_NAME + ") NULL DEFAULT NULL," +
                    "`name_add` VARCHAR(" + MAX_FULL_NAME + ")" +
                    " NULL DEFAULT NULL,`unit_id` INT(11) NULL DEFAULT NULL," +
                    " PRIMARY KEY (`id`), INDEX `fk_unit_idx` (`unit_id` ASC)," +
                    "CONSTRAINT `fk_unit` FOREIGN KEY (`unit_id`) REFERENCES" +
                    " `" + TABLE_NAME_UNIT_OF_MEASURE + "` (`id`) ON DELETE " +
                    "NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB " +
                    "DEFAULT CHARACTER SET = utf8;";

    static final String MYSQL_NEW_TABLE_UNIT =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_UNIT_OF_MEASURE +
                    "` (`id` INT(11) NOT NULL AUTO_INCREMENT,`name` VARCHAR(" + MAX_NAME + ") NOT NULL," +
                    "`full_name` VARCHAR(" + MAX_FULL_NAME + ") NULL DEFAULT NULL,PRIMARY KEY (`id`))" +
                    " ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    //-----------------------------------------------------------------------------------------------

    static final String MYSQL_ADD_UNIT_OF_MEASURE = "INSERT INTO " + TABLE_NAME_UNIT_OF_MEASURE +
            " (name, full_name) VALUE (?,?)";
    static final String MYSQL_DELL_UNIT_OF_MEASURE = "DELETE FROM " + TABLE_NAME_UNIT_OF_MEASURE + " where id=?;";
    static final String MYSQL_EDIT_UNIT_OF_MEASURE = "UPDATE " + TABLE_NAME_UNIT_OF_MEASURE + " SET name=?, " +
            "full_name=? WHERE id=?;";
    static final String MYSQL_GET_UNIT_OF_MEASURE = "SELECT * FROM " + TABLE_NAME_UNIT_OF_MEASURE + " WHERE id=?;";

    static final String MYSQL_ADD_GOOD = "INSERT INTO " + TABLE_NAME_GOOD +
            " (name, full_name, name_add, unit_id) VALUE (?,?,?,?)";
    static final String MYSQL_DELL_GOOD = "DELETE FROM " + TABLE_NAME_GOOD + " where id=?;";
    static final String MYSQL_EDIT_GOOD = "UPDATE " + TABLE_NAME_GOOD + " SET name=?, " +
            "full_name=?, name_add=?, unit_id=? WHERE id=?;";
    static final String MYSQL_GET_GOOD = "SELECT * FROM " + TABLE_NAME_GOOD + " WHERE id=?;";

}
