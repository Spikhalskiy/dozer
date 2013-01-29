package org.dozer.functional_tests;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;
import org.dozer.vo.proto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dmitry Spikhalskiy
 * @since 17.01.13
 */
public class ProtoBeansWithCustomConverterTest extends ProtoAbstractTest {
  private DozerBeanMapper mapper;

  @Before
  public void setUp() throws Exception {
    mapper = getMapper("protoBeansMappingByCustomConverter.xml");
  }

  @Test
  public void testFieldConverter_toProto() {
    TestObjectContainer source = new TestObjectContainer();
    TestObject innerTestObject = new TestObject();
    innerTestObject.setOne("One");

    source.setOne("smth");
    source.setNested(innerTestObject);

    ProtoTestObjects.ProtoTestObjectWithNestedProtoObject result = mapper.map(source, ProtoTestObjects.ProtoTestObjectWithNestedProtoObject.class);
    assertNotNull(result);
    Assert.assertNotNull(result.getNestedObject());
    Assert.assertEquals("One", result.getNestedObject().getOne());
  }

  @Test
  public void testClassLevelConverter_toProto() {
    TestObject source = new TestObject();
    source.setOne("One");

    ProtoTestObjects.SimpleProtoTestObject result = mapper.map(source, ProtoTestObjects.SimpleProtoTestObject.class);
    assertNotNull(result);
    Assert.assertEquals("One", result.getOne());
  }

  public static class CustomConv extends DozerConverter<TestObject, ProtoTestObjects.SimpleProtoTestObject> {
    public CustomConv() {
      super(TestObject.class, ProtoTestObjects.SimpleProtoTestObject.class);
    }

    @Override
    public ProtoTestObjects.SimpleProtoTestObject convertTo(TestObject source, ProtoTestObjects.SimpleProtoTestObject destination) {
      ProtoTestObjects.SimpleProtoTestObject.Builder builder = ProtoTestObjects.SimpleProtoTestObject.newBuilder();
      if (source.getOne() != null) builder.setOne(source.getOne());
      return builder.build();
    }

    @Override
    public TestObject convertFrom(ProtoTestObjects.SimpleProtoTestObject source, TestObject destination) {
      TestObject result = new TestObject();
      result.setOne(source.hasOne() ? source.getOne() : null);
      return result;
    }
  }
}
