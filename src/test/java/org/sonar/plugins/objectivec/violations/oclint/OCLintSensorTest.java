/*
 * Sonar Objective-C Plugin
 * Copyright (C) 2012 OCTO Technology, Backelite
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.objectivec.violations.oclint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.plugins.objectivec.core.ObjectiveC;
import org.sonar.plugins.objectivec.violations.oclint.OCLintSensor;

import java.util.SortedSet;
import java.util.TreeSet;

public final class OCLintSensorTest {

    private Settings settings;

    @Before
    public void setUp() {
        settings = new Settings();
    }

	@Test
	public void shouldExecuteOnProjectShouldBeTrueWhenProjectIsObjc() {
		final Project project = new Project("Test");

        ResourcePerspectives resourcePerspectives = mock(ResourcePerspectives.class);
        FileSystem fileSystem = mock(FileSystem.class);
        SortedSet<String> languages = new TreeSet<String>();
        languages.add(ObjectiveC.KEY);
        when(fileSystem.languages()).thenReturn(languages);

		final OCLintSensor testedSensor = new OCLintSensor(fileSystem, settings, resourcePerspectives);

		assertTrue(testedSensor.shouldExecuteOnProject(project));
	}

	@Test
	public void shouldExecuteOnProjectShouldBeFalseWhenProjectIsSomethingElse() {
		final Project project = new Project("Test");

        ResourcePerspectives resourcePerspectives = mock(ResourcePerspectives.class);
        FileSystem fileSystem = mock(FileSystem.class);
        SortedSet<String> languages = new TreeSet<String>();
        languages.add("Test");
        when(fileSystem.languages()).thenReturn(languages);

		final OCLintSensor testedSensor = new OCLintSensor(fileSystem, settings, resourcePerspectives);

		assertFalse(testedSensor.shouldExecuteOnProject(project));
	}

}
