package com.doublechaintech.retailscm.retailstorecreation;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.doublechaintech.retailscm.expression.SmartListExpression;

import java.util.List;

public abstract class RetailStoreCreationSmartListExpression<T>
    extends SmartListExpression<T, RetailStoreCreation> {
  public RetailStoreCreationExpression<T> first() {
    return new RetailStoreCreationExpression<T>() {
      @Override
      public RetailStoreCreation eval(T l) {
        List<RetailStoreCreation> list = RetailStoreCreationSmartListExpression.this.eval(l);
        return CollectionUtil.getFirst(list);
      }

      @Override
      public T $getRoot() {
        return RetailStoreCreationSmartListExpression.this.$getRoot();
      }
    };
  }

  public RetailStoreCreationExpression<T> get(int index) {
    return new RetailStoreCreationExpression<T>() {
      @Override
      public RetailStoreCreation eval(T l) {
        List<RetailStoreCreation> list = RetailStoreCreationSmartListExpression.this.eval(l);
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
        return RetailStoreCreationSmartListExpression.this.$getRoot();
      }
    };
  }
}
