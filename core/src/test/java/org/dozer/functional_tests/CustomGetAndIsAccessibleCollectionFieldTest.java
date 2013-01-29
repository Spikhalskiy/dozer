package org.dozer.functional_tests;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author Dmitry Spikhalskiy
 * @since 30.01.13
 */
public class CustomGetAndIsAccessibleCollectionFieldTest extends AbstractFunctionalTest {
  @Override
  @Before
  public void setUp() throws Exception {
    mapper = getMapper("customGetAndIsAccessibleCollectionFieldMapping.xml");
  }

  @Test
  public void convertClassWithCustomGetCollectionToIsAccessibleCollection() {
    FromClass source = new FromClass(Arrays.asList(10L));
    ToClass destination = mapper.map(source, ToClass.class);
    assertEquals(1, destination.internalLongGetter().size());
  }

  public static class FromClass {
    private List<Long> longCollection;

    public FromClass() {
    }

    public FromClass(List<Long> longCollection) {
      this.longCollection = longCollection;
    }

    public List<Long> getLong() {
      return longCollection;
    }
  }

  public static class ToClass {
    private List<Long> _long;

    public List<Long> internalLongGetter() {
      return _long;
    }
  }
}
