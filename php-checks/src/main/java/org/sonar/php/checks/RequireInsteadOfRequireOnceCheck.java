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

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

@Rule(key = RequireInsteadOfRequireOnceCheck.KEY)
public class RequireInsteadOfRequireOnceCheck extends PHPVisitorCheck {

  public static final String KEY = "S2003";
  private static final String MESSAGE = "Replace \"%s\" with \"%s\".";

  private static final List<String> WRONG_FUNCTIONS = Arrays.asList("require", "include");

  @Override
  public void visitFunctionCall(FunctionCallTree tree) {
    super.visitFunctionCall(tree);

    String callee = tree.callee().toString();

    if (WRONG_FUNCTIONS.contains(callee.toLowerCase(Locale.ENGLISH))) {
      String message = String.format(MESSAGE, callee, callee + "_once");
      context().newIssue(this, tree.callee(), message);
    }
  }

}
