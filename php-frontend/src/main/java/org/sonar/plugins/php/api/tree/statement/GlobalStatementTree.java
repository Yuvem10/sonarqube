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
package org.sonar.plugins.php.api.tree.statement;

import org.sonar.plugins.php.api.tree.SeparatedList;
import org.sonar.plugins.php.api.tree.expression.VariableTree;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;

/**
 * <a href="http://php.net/manual/en/language.variables.scope.php#language.variables.scope.global">Global</a> variable declaration
 * <pre>
 *   global {@link #variables()} ;
 * </pre>
 */
public interface GlobalStatementTree extends StatementTree {

  SyntaxToken globalToken();

  /**
   * Members can be:
   * <ul>
   *   <li>{@link Kind#VARIABLE_IDENTIFIER variable identifier}
   *   <li>{@link Kind#VARIABLE_VARIABLE variable variable}
   *   <li>{@link Kind#COMPOUND_VARIABLE_NAME compound variable name}
   * <ul/>
   */
  SeparatedList<VariableTree> variables();

  SyntaxToken eosToken();

}
