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
package org.sonar.php.checks;

import java.io.File;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.sonar.plugins.php.CheckVerifier;

import static org.sonar.php.checks.phpini.PhpIniCheckTestUtils.check;
import static org.sonar.php.checks.phpini.PhpIniCheckTestUtils.issue;

class CookiesSecureCheckTest {

  private CookiesSecureCheck check = new CookiesSecureCheck();
  private File dir = new File("src/test/resources/checks/phpini");

  @Test
  void testPhpFile() {
    CheckVerifier.verify(new CookiesSecureCheck(), "CookiesSecureCheck.php");
  }

  @Test
  void testPhpIni() {
    check(check, new File(dir, "cookie_secure.ini"));
    check(check, new File(dir, "empty.ini"),
      Collections.singletonList(issue("Make sure creating the session cookie without the \"secure\" flag is safe here.")));
  }

}
