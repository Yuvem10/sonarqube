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

import java.util.List;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;

/**
 * <a href="http://php.net/manual/en/language.types.string.php#language.types.string.syntax.heredoc">Heredoc strings</a>
 */
public interface HeredocStringLiteralTree extends ExpressionTree {

  // <<< XYZ
  SyntaxToken openingToken();

  List<ExpandableStringCharactersTree> strings();

  /**
   * Expressions can be:
   * <ul>
   *   <li>{@link Kind#EXPANDABLE_STRING_CHARACTERS expandable string characters}
   *   <li>{@link Kind#VARIABLE_IDENTIFIER variable identifier}
   *   <li>{@link Kind#COMPUTED_VARIABLE_NAME computed variable name}
   *   <li>{@link Kind#COMPOUND_VARIABLE_NAME compound variable name}
   *   <li>{@link Kind#ARRAY_ACCESS array access}
   *   <li>{@link Kind#COMPOUND_VARIABLE_NAME member access}
   * </ul>
   */
  List<ExpressionTree> expressions();

  // XYZ
  SyntaxToken closingToken();

}
