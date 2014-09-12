package org.dozer.vo.generic_collection;

import java.util.ArrayList;
import java.util.List;

import org.dozer.vo.Fruit;

/**
 * @author Dmitry Spikhalskiy <dmitry@spikhalskiy.com>
 */
public class B {
  private List<Class<? extends Fruit>> listOfClasses = new ArrayList<Class<? extends Fruit>>();

  public List<Class<? extends Fruit>> getListOfClasses() {
    return listOfClasses;
  }

  public void setListOfClasses(List<Class<? extends Fruit>> listOfClasses) {
    this.listOfClasses = listOfClasses;
  }
}
