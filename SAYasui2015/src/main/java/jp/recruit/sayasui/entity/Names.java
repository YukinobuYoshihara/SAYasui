package jp.recruit.sayasui.entity;

import javax.annotation.Generated;
import jp.recruit.sayasui.entity.ContentsNames._ContentsNames;
import jp.recruit.sayasui.entity.ItemNames._ItemNames;
import jp.recruit.sayasui.entity.OrdersNames._OrdersNames;
import jp.recruit.sayasui.entity.StockNames._StockNames;
import jp.recruit.sayasui.entity.YasuiUserNames._YasuiUserNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2015/07/03 9:20:09")
public class Names {

    /**
     * {@link Contents}の名前クラスを返します。
     * 
     * @return Contentsの名前クラス
     */
    public static _ContentsNames contents() {
        return new _ContentsNames();
    }

    /**
     * {@link Item}の名前クラスを返します。
     * 
     * @return Itemの名前クラス
     */
    public static _ItemNames item() {
        return new _ItemNames();
    }

    /**
     * {@link Orders}の名前クラスを返します。
     * 
     * @return Ordersの名前クラス
     */
    public static _OrdersNames orders() {
        return new _OrdersNames();
    }

    /**
     * {@link Stock}の名前クラスを返します。
     * 
     * @return Stockの名前クラス
     */
    public static _StockNames stock() {
        return new _StockNames();
    }

    /**
     * {@link YasuiUser}の名前クラスを返します。
     * 
     * @return YasuiUserの名前クラス
     */
    public static _YasuiUserNames yasuiUser() {
        return new _YasuiUserNames();
    }
}