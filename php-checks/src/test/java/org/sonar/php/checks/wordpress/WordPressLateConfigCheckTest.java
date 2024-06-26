/*
 * SonarQube PHP Plugin
 * Copyright (C) 2010-2023 SonarSource SA
 * mailto:info AT sonarsource DOT com
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.php.checks.wordpress;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.sonar.plugins.php.CheckVerifier;
import org.sonar.plugins.php.api.visitors.PHPCheck;

class WordPressLateConfigCheckTest extends WordPressConfigCheckTest {

  @TempDir
  public static File tmpDir;

  PHPCheck check = new WordPressLateConfigCheck();

  public WordPressLateConfigCheckTest() {
    super(tmpDir);
  }

  @Test
  void testConfigFileWithParenthesis() throws IOException {
    wordPressVerifier.verify(check, "wordpress/WordPressLateConfigCheck/with-parenthesis.php");
  }

  @Test
  void testConfigFileWithoutParenthesis() throws IOException {
    wordPressVerifier.verify(check, "wordpress/WordPressLateConfigCheck/without-parenthesis.php");
  }

  @Test
  void testOtherFile() {
    CheckVerifier.verifyNoIssue(check, "wordpress/WordPressLateConfigCheck/no-wp-config.php");
  }

}
