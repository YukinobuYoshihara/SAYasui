package jp.recruit.sayasui.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Contents}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/07/03 9:20:09")
public class ContentsNames {

    /**
     * midのプロパティ名を返します。
     * 
     * @return midのプロパティ名
     */
    public static PropertyName<String> mid() {
        return new PropertyName<String>("mid");
    }

    /**
     * titleのプロパティ名を返します。
     * 
     * @return titleのプロパティ名
     */
    public static PropertyName<String> title() {
        return new PropertyName<String>("title");
    }

    /**
     * keywdのプロパティ名を返します。
     * 
     * @return keywdのプロパティ名
     */
    public static PropertyName<String> keywd() {
        return new PropertyName<String>("keywd");
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
     * skipのプロパティ名を返します。
     * 
     * @return skipのプロパティ名
     */
    public static PropertyName<Short> skip() {
        return new PropertyName<Short>("skip");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _ContentsNames extends PropertyName<Contents> {

        /**
         * インスタンスを構築します。
         */
        public _ContentsNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ContentsNames(final String name) {
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
        public _ContentsNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * midのプロパティ名を返します。
         *
         * @return midのプロパティ名
         */
        public PropertyName<String> mid() {
            return new PropertyName<String>(this, "mid");
        }

        /**
         * titleのプロパティ名を返します。
         *
         * @return titleのプロパティ名
         */
        public PropertyName<String> title() {
            return new PropertyName<String>(this, "title");
        }

        /**
         * keywdのプロパティ名を返します。
         *
         * @return keywdのプロパティ名
         */
        public PropertyName<String> keywd() {
            return new PropertyName<String>(this, "keywd");
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
         * skipのプロパティ名を返します。
         *
         * @return skipのプロパティ名
         */
        public PropertyName<Short> skip() {
            return new PropertyName<Short>(this, "skip");
        }
    }
}