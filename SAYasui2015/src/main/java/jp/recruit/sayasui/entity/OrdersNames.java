package jp.recruit.sayasui.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Orders}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/07/03 9:20:09")
public class OrdersNames {

    /**
     * oidのプロパティ名を返します。
     * 
     * @return oidのプロパティ名
     */
    public static PropertyName<String> oid() {
        return new PropertyName<String>("oid");
    }

    /**
     * userNameのプロパティ名を返します。
     * 
     * @return userNameのプロパティ名
     */
    public static PropertyName<String> userName() {
        return new PropertyName<String>("userName");
    }

    /**
     * itemIdのプロパティ名を返します。
     * 
     * @return itemIdのプロパティ名
     */
    public static PropertyName<String> itemId() {
        return new PropertyName<String>("itemId");
    }

    /**
     * quantityのプロパティ名を返します。
     * 
     * @return quantityのプロパティ名
     */
    public static PropertyName<Integer> quantity() {
        return new PropertyName<Integer>("quantity");
    }

    /**
     * isDeliveryのプロパティ名を返します。
     * 
     * @return isDeliveryのプロパティ名
     */
    public static PropertyName<Short> isDelivery() {
        return new PropertyName<Short>("isDelivery");
    }

    /**
     * orderDateのプロパティ名を返します。
     * 
     * @return orderDateのプロパティ名
     */
    public static PropertyName<Date> orderDate() {
        return new PropertyName<Date>("orderDate");
    }

    /**
     * deliveryDateのプロパティ名を返します。
     * 
     * @return deliveryDateのプロパティ名
     */
    public static PropertyName<Date> deliveryDate() {
        return new PropertyName<Date>("deliveryDate");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _OrdersNames extends PropertyName<Orders> {

        /**
         * インスタンスを構築します。
         */
        public _OrdersNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _OrdersNames(final String name) {
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
        public _OrdersNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * oidのプロパティ名を返します。
         *
         * @return oidのプロパティ名
         */
        public PropertyName<String> oid() {
            return new PropertyName<String>(this, "oid");
        }

        /**
         * userNameのプロパティ名を返します。
         *
         * @return userNameのプロパティ名
         */
        public PropertyName<String> userName() {
            return new PropertyName<String>(this, "userName");
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
         * quantityのプロパティ名を返します。
         *
         * @return quantityのプロパティ名
         */
        public PropertyName<Integer> quantity() {
            return new PropertyName<Integer>(this, "quantity");
        }

        /**
         * isDeliveryのプロパティ名を返します。
         *
         * @return isDeliveryのプロパティ名
         */
        public PropertyName<Short> isDelivery() {
            return new PropertyName<Short>(this, "isDelivery");
        }

        /**
         * orderDateのプロパティ名を返します。
         *
         * @return orderDateのプロパティ名
         */
        public PropertyName<Date> orderDate() {
            return new PropertyName<Date>(this, "orderDate");
        }

        /**
         * deliveryDateのプロパティ名を返します。
         *
         * @return deliveryDateのプロパティ名
         */
        public PropertyName<Date> deliveryDate() {
            return new PropertyName<Date>(this, "deliveryDate");
        }
    }
}