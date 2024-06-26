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
package org.sonar.plugins.php.api.tree.expression;

import org.sonar.plugins.php.api.tree.SeparatedList;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;

/**
 * <a href="http://php.net/manual/en/functions.anonymous.php">Lexical Variable</a>: use construct that allows
 * an anonymous function to inherit from parent scope variables.
 * <pre>
 *   use ( {@link #variables()} )
 * </pre>
 *
 */
public interface LexicalVariablesTree extends ExpressionTree {

  SyntaxToken useToken();

  SyntaxToken openParenthesisToken();

  /**
   * Variables can be:
   * <ul>
   *   <li>{@link Kind#REFERENCE_VARIABLE Reference variable}
   *   <li>{@link Kind#VARIABLE_IDENTIFIER Variable identifier}
   * <ul/>
   */
  SeparatedList<VariableTree> variables();

  SyntaxToken closeParenthesisToken();

}
