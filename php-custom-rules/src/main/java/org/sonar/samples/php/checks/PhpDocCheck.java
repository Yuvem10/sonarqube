/*
 * SonarQube PHP Plugin
 * Copyright (C) 2016-2023 SonarSource SA
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
package org.sonar.samples.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.cache.CacheContext;
import org.sonar.plugins.php.api.tree.CompilationUnitTree;
import org.sonar.plugins.php.api.tree.ScriptTree;
import org.sonar.plugins.php.api.tree.declaration.ClassDeclarationTree;
import org.sonar.plugins.php.api.tree.declaration.FunctionDeclarationTree;
import org.sonar.plugins.php.api.tree.declaration.MethodDeclarationTree;
import org.sonar.plugins.php.api.tree.expression.FunctionExpressionTree;
import org.sonar.plugins.php.api.tree.expression.IdentifierTree;
import org.sonar.plugins.php.api.tree.expression.NameIdentifierTree;
import org.sonar.plugins.php.api.tree.expression.VariableIdentifierTree;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.php.api.tree.statement.BlockTree;
import org.sonar.plugins.php.api.visitors.CheckContext;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;
import org.sonar.plugins.php.api.visitors.PhpFile;
import org.sonar.plugins.php.api.visitors.PhpInputFileContext;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example of implementation of a check by extending {@link PHPVisitorCheck}.
 * PHPVisitorCheck provides methods to visit nodes of the Abstract Syntax Tree
 * that represents the source code.
 * <p>
 * Those methods can be overridden to process information
 * related to node and issue can be created via the context that can be
 * accessed through {@link PHPVisitorCheck#context()}.
 */
@Rule(
  key = PhpDocCheck.KEY,
  priority = Priority.MAJOR,
  name = "This function must contain a phpdoc",
  tags = {"convention"})
public class PhpDocCheck extends PHPVisitorCheck {

  public static final String KEY = "S3";

  @Override
  public void visitMethodDeclaration(MethodDeclarationTree tree) {

    String globalContext = context().getPhpFile().contents();
    final String name = tree.name().text();
    final String regexDoc = "\\/\\*\\*\\s(.*?[\\s\\S]*)\\*\\/\\s+public\\s+function\\s+" + name + "\\(|\\/\\*\\*\\s(.*?[\\s\\S]*)\\*\\/\\s+private\\s+function\\s+" + name
      + "\\(|\\/\\*\\*\\s(.*?[\\s\\S]*)\\*\\/\\s+function\\s+" + name + "\\(|\\/\\*\\*\\s(.*?[\\s\\S]*)\\*\\/\\s+\\/+[a-zA-Z0-9\\s]+\\s+public\\s+function\\s+" + name
      + "\\(|\\/\\*\\*\\s(.*?[\\s\\S]*)\\*\\/\\s+\\/\\/[a-zA-Z0-9\\s]+\\s+function\\s+" + name + "\\(|\\/\\*\\*\\s(.*?[\\s\\S]*)\\*\\/\\s+\\/\\/[a-zA-Z0-9\\s]+\\s+function\\s+"
      + name + "\\(";
    final Pattern pattern = Pattern.compile(regexDoc);
    final Matcher matcher = pattern.matcher(globalContext);

    if (!matcher.find()) {
      context().newIssue(this, tree, "");
    }

    super.visitMethodDeclaration(tree);
  }

}
