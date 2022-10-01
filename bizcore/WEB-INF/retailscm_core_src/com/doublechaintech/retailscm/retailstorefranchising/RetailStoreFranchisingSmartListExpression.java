package com.doublechaintech.retailscm.retailstorefranchising;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.doublechaintech.retailscm.expression.SmartListExpression;

import java.util.List;

public abstract class RetailStoreFranchisingSmartListExpression<T>
    extends SmartListExpression<T, RetailStoreFranchising> {
  public RetailStoreFranchisingExpression<T> first() {
    return new RetailStoreFranchisingExpression<T>() {
      @Override
      public RetailStoreFranchising eval(T l) {
        List<RetailStoreFranchising> list = RetailStoreFranchisingSmartListExpression.this.eval(l);
        return CollectionUtil.getFirst(list);
      }

      @Override
      public T $getRoot() {
        return RetailStoreFranchisingSmartListExpression.this.$getRoot();
      }
    };
  }

  public RetailStoreFranchisingExpression<T> get(int index) {
    return new RetailStoreFranchisingExpression<T>() {
      @Override
      public RetailStoreFranchising eval(T l) {
        List<RetailStoreFranchising> list = RetailStoreFranchisingSmartListExpression.this.eval(l);
        if (ObjectUtil.isEmpty(list)) {
          return null;
        }

        if (index < 0 || index > list.size() - 1) {
          return null;
        }
        return list.get(index);
      }

      @Override
      public T $getRoot() {
        return RetailStoreFranchisingSmartListExpression.this.$getRoot();
      }
    };
  }
}
