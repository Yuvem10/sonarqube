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
package org.sonar.php.api;

import org.sonar.sslr.grammar.GrammarRuleKey;

public enum PHPPunctuator implements GrammarRuleKey {

  ANDEQUAL("&="),
  LOWER_BINARY_CAST_PREFIX("b"),
  UPPER_BINARY_CAST_PREFIX("B"),
  CONCATEQUAL(".="),
  DIVEQUAL("/="),
  EQU("="),
  EQUAL("=="),
  NOTEQUAL("!="),
  NOTEQUALBIS("<>"),
  EQUAL2("==="),
  NOTEQUAL2("!=="),
  SPACESHIP("<=>"),
  LT("<"),
  GT(">"),
  LE("<="),
  GE(">="),
  STAR_EQU("*="),
  STAR_STAR_EQU("**="),
  MOD_EQU("%="),
  MINUS_EQU("-="),
  OR_EQU("|="),
  PLUS_EQU("+="),
  SL_EQU("<<="),
  SR_EQU(">>="),
  SL("<<"),
  SR(">>"),
  XOR_EQU("^="),
  NULL_COALESCING_EQU("??="),

  PLUS("+"),
  MINUS("-"),
  TILDA("~"),

  STAR_STAR("**"),

  XOR("^"),
  STAR("*"),
  MOD("%"),
  DIV("/"),
  INC("++"),
  DEC("--"),
  ANDAND("&&"),
  AMPERSAND("&"),
  OROR("||"),
  OR("|"),

  NULL_COALESCE("??"),

  ARROW("->"),
  NULL_SAFE_ARROW("?->"),
  DOUBLEARROW("=>"),

  DOLLAR("$"),
  DOLLAR_LCURLY("${"),
  LCURLYBRACE("{"),
  RCURLYBRACE("}"),
  LPARENTHESIS("("),
  RPARENTHESIS(")"),
  LBRACKET("["),
  RBRACKET("]"),
  DOT("."),
  SEMICOLON(";"),
  COMMA(","),
  ELLIPSIS("..."),

  AT("@"),
  BANG("!"),
  QUERY("?"),
  DOUBLECOLON("::"),
  COLON(":"),
  NS_SEPARATOR("\\"),

  ATTRIBUTE_OPEN("#[");

  private final String value;

  PHPPunctuator(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
