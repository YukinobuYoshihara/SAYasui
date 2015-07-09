package jp.recruit.sayasui.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Stock}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/07/03 9:20:09")
public class StockNames {

    /**
     * itemIdのプロパティ名を返します。
     * 
     * @return itemIdのプロパティ名
     */
    public static PropertyName<String> itemId() {
        return new PropertyName<String>("itemId");
    }

    /**
     * stockNumのプロパティ名を返します。
     * 
     * @return stockNumのプロパティ名
     */
    public static PropertyName<Integer> stockNum() {
        return new PropertyName<Integer>("stockNum");
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
    public static class _StockNames extends PropertyName<Stock> {

        /**
         * インスタンスを構築します。
         */
        public _StockNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _StockNames(final String name) {
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
        public _StockNames(final PropertyName<?> parent, final String name) {
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
         * stockNumのプロパティ名を返します。
         *
         * @return stockNumのプロパティ名
         */
        public PropertyName<Integer> stockNum() {
            return new PropertyName<Integer>(this, "stockNum");
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