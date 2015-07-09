package jp.recruit.sayasui.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Item}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/07/03 9:20:09")
public class ItemNames {

    /**
     * itemIdのプロパティ名を返します。
     * 
     * @return itemIdのプロパティ名
     */
    public static PropertyName<String> itemId() {
        return new PropertyName<String>("itemId");
    }

    /**
     * itemNameのプロパティ名を返します。
     * 
     * @return itemNameのプロパティ名
     */
    public static PropertyName<String> itemName() {
        return new PropertyName<String>("itemName");
    }

    /**
     * imgurlのプロパティ名を返します。
     * 
     * @return imgurlのプロパティ名
     */
    public static PropertyName<String> imgurl() {
        return new PropertyName<String>("imgurl");
    }

    /**
     * itemSizeのプロパティ名を返します。
     * 
     * @return itemSizeのプロパティ名
     */
    public static PropertyName<String> itemSize() {
        return new PropertyName<String>("itemSize");
    }

    /**
     * priceのプロパティ名を返します。
     * 
     * @return priceのプロパティ名
     */
    public static PropertyName<Integer> price() {
        return new PropertyName<Integer>("price");
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
    public static class _ItemNames extends PropertyName<Item> {

        /**
         * インスタンスを構築します。
         */
        public _ItemNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ItemNames(final String name) {
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
        public _ItemNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * itemIdのプロパティ名を返します。
         *
         * @return itemIdのプロパティ名
         */
        public PropertyName<String> itemId() {
            return new PropertyName<String>(this, "itemId");
        }

        /**
         * itemNameのプロパティ名を返します。
         *
         * @return itemNameのプロパティ名
         */
        public PropertyName<String> itemName() {
            return new PropertyName<String>(this, "itemName");
        }

        /**
         * imgurlのプロパティ名を返します。
         *
         * @return imgurlのプロパティ名
         */
        public PropertyName<String> imgurl() {
            return new PropertyName<String>(this, "imgurl");
        }

        /**
         * itemSizeのプロパティ名を返します。
         *
         * @return itemSizeのプロパティ名
         */
        public PropertyName<String> itemSize() {
            return new PropertyName<String>(this, "itemSize");
        }

        /**
         * priceのプロパティ名を返します。
         *
         * @return priceのプロパティ名
         */
        public PropertyName<Integer> price() {
            return new PropertyName<Integer>(this, "price");
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