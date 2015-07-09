package jp.recruit.sayasui.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link YasuiUser}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/07/03 9:20:09")
public class YasuiUserNames {

    /**
     * userIdのプロパティ名を返します。
     * 
     * @return userIdのプロパティ名
     */
    public static PropertyName<String> userId() {
        return new PropertyName<String>("userId");
    }

    /**
     * nameのプロパティ名を返します。
     * 
     * @return nameのプロパティ名
     */
    public static PropertyName<String> name() {
        return new PropertyName<String>("name");
    }

    /**
     * passwdのプロパティ名を返します。
     * 
     * @return passwdのプロパティ名
     */
    public static PropertyName<String> passwd() {
        return new PropertyName<String>("passwd");
    }

    /**
     * descriptのプロパティ名を返します。
     * 
     * @return descriptのプロパティ名
     */
    public static PropertyName<String> descript() {
        return new PropertyName<String>("descript");
    }

    /**
     * roleのプロパティ名を返します。
     * 
     * @return roleのプロパティ名
     */
    public static PropertyName<String> role() {
        return new PropertyName<String>("role");
    }

    /**
     * isDeleteのプロパティ名を返します。
     * 
     * @return isDeleteのプロパティ名
     */
    public static PropertyName<Short> isDelete() {
        return new PropertyName<Short>("isDelete");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _YasuiUserNames extends PropertyName<YasuiUser> {

        /**
         * インスタンスを構築します。
         */
        public _YasuiUserNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _YasuiUserNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _YasuiUserNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * userIdのプロパティ名を返します。
         *
         * @return userIdのプロパティ名
         */
        public PropertyName<String> userId() {
            return new PropertyName<String>(this, "userId");
        }

        /**
         * nameのプロパティ名を返します。
         *
         * @return nameのプロパティ名
         */
        public PropertyName<String> name() {
            return new PropertyName<String>(this, "name");
        }

        /**
         * passwdのプロパティ名を返します。
         *
         * @return passwdのプロパティ名
         */
        public PropertyName<String> passwd() {
            return new PropertyName<String>(this, "passwd");
        }

        /**
         * descriptのプロパティ名を返します。
         *
         * @return descriptのプロパティ名
         */
        public PropertyName<String> descript() {
            return new PropertyName<String>(this, "descript");
        }

        /**
         * roleのプロパティ名を返します。
         *
         * @return roleのプロパティ名
         */
        public PropertyName<String> role() {
            return new PropertyName<String>(this, "role");
        }

        /**
         * isDeleteのプロパティ名を返します。
         *
         * @return isDeleteのプロパティ名
         */
        public PropertyName<Short> isDelete() {
            return new PropertyName<Short>(this, "isDelete");
        }
    }
}