package org.dozer.functional_tests;

import java.util.ArrayList;
import java.util.List;

import org.dozer.vo.Apple;
import org.dozer.vo.Orange;
import org.dozer.vo.generic_collection.A;
import org.dozer.vo.generic_collection.B;
import org.dozer.vo.Fruit;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * @author Dmitry Spikhalskiy <dmitry@spikhalskiy.com>
 */
public class GenericCollectionMapping2Test extends AbstractFunctionalTest {
  @Override
  @Before
  public void setUp() throws Exception {
    mapper = getMapper("genericCollectionMapping2.xml");
  }

  @Test
  public void testListOfListToListOfList() throws Exception {
    List<Class<? extends Fruit>> listList = newInstance(ArrayList.class);
    listList.add(Orange.class);
    listList.add(Apple.class);
    A to = newInstance(A.class);
    to.setListOfClasses(listList);

    B top = mapper.map(to, B.class);
    assertEquals(Orange.class, top.getListOfClasses().get(0));
    assertEquals(Apple.class, top.getListOfClasses().get(1));

    A toDest = mapper.map(top, A.class);
    assertEquals(Orange.class, toDest.getListOfClasses().get(0));
    assertEquals(Apple.class, toDest.getListOfClasses().get(1));
  }
}
