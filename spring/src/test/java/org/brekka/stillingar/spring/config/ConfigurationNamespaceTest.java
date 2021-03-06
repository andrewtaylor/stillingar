/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.brekka.stillingar.spring.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * A very simple test that the spring based stillingar configuration namespace is working, using properties based configuration.
 *
 * @author Andrew Taylor (andrew@brekka.org)
 */
@ContextConfiguration
public class ConfigurationNamespaceTest extends AbstractJUnit4SpringContextTests {

    
    @Test
    public void checkStandardConstructorReplacement() {
        assertEquals("Value One", applicationContext.getBean("standardBean", TheBean.class).getFromConstructor());
    }
    @Test
    public void checkStandardPropertyReplacement() {
        assertEquals("Value Two", applicationContext.getBean("standardBean", TheBean.class).getProperty());
    }
    
    @Test
    public void checkNestedConstructorReplacement() {
        assertEquals("Value is (Internal)", applicationContext.getBean("nestedBean", TheBean.class).getFromConstructor());
    }
    @Test
    public void checkNestedPropertyReplacement() {
        assertEquals("Other Value is (Internal)", applicationContext.getBean("nestedBean", TheBean.class).getProperty());
    }
    
}
