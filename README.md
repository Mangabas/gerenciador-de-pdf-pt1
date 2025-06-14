# Sistema de Biblioteca Digital

Um sistema de gerenciamento de biblioteca digital desenvolvido em Java que permite organizar e gerenciar documentos PDF (livros, slides e notas de aula) em bibliotecas e coleções personalizadas.

## 📋 Funcionalidades

### Gerenciamento de Bibliotecas
- **Criar biblioteca**: Criação de novas bibliotecas com estrutura de diretórios organizada
- **Trocar biblioteca**: Alternância entre diferentes bibliotecas existentes
- **Listar bibliotecas**: Visualização de todas as bibliotecas disponíveis

### Gerenciamento de Documentos PDF
- **Adicionar livros**: Inclusão de livros com metadados completos (autor, título, subtítulo, área, ano)
- **Adicionar slides**: Adição de apresentações com informações da disciplina
- **Adicionar notas de aula**: Inclusão de notas com subtítulo e disciplina
- **Listar PDFs**: Visualização de todos os documentos na biblioteca atual


## 🏗️ Estrutura do Projeto

```
├── Main.java                          # Classe principal com interface de comandos
├── biblioteca/
│   └── BibliotecaService.java         # Serviços de gerenciamento de biblioteca
├── models/
│   ├── ArquivoPDF.java               # Classe abstrata base para documentos
│   ├── Livro.java                    # Modelo para livros
│   ├── Slide.java                    # Modelo para slides
│   ├── NotaDeAula.java               # Modelo para notas de aula
└── utils/
    ├── LibMethods.java               # Métodos para gerenciamento de bibliotecas
    └── SimpleSerializationLib.java   # Utilitário de serialização
```

## 🚀 Como Usar

### Compilação
```bash
javac -cp . Main.java biblioteca/*.java models/*.java utils/*.java 
```

### Comandos Principais

#### Gerenciamento de Biblioteca
```bash
# Criar nova biblioteca
java Main create-lib <nome_biblioteca>

# Listar bibliotecas disponíveis
java Main list-libs

# Trocar biblioteca atual
java Main change-lib
```

#### Adição de Documentos
```bash
# Adicionar livro
java Main add-book <autor> <titulo> <subtitulo> <area> <ano> <caminho_pdf>

# Adicionar slide
java Main add-slide <titulo> <disciplina> <autor> <caminho_pdf>

# Adicionar notas de aula
java Main add-notes <titulo> <subtitulo> <disciplina> <autor> <caminho_pdf>

# Listar todos os PDFs
java Main list-pdf

### Exemplo de Uso Completo

```bash
# 1. Compilar o projeto
javac -cp . Main.java biblioteca/*.java models/*.java utils/*.java 

# 2. Criar uma biblioteca
java Main create-lib MinhaBlioteca

# 3. Adicionar documentos
java Main add-book "João Silva" "Programação Java" "Conceitos Avançados" "Computação" 2023 "/caminho/para/livro.pdf"
java Main add-slide "Introdução à POO" "Programação" "Prof. Maria" "/caminho/para/slides.pdf"

# 4. Listar documentos
java Main list-pdf
```

## 📁 Estrutura de Armazenamento

O sistema cria automaticamente uma estrutura de diretórios organizada:

```
bin/
├── <nome_biblioteca>/
│   ├── Book/           # Livros
│   ├── Slide/          # Apresentações
│   └── Notes/          # Notas de aula
├── Collections/        # Coleções personalizadas
└── *.ser              # Arquivos de serialização
```

## ⚙️ Recursos Técnicos

- **Serialização**: Persistência de dados usando serialização Java nativa
- **Cópia de Arquivos**: Cópia automática de PDFs para estrutura organizada

## 🔧 Requisitos

- **Java 8+**: Para suporte a streams e recursos modernos
- **Sistema de arquivos**: Permissões de leitura/escrita no diretório de trabalho
- **Documentos PDF**: Arquivos devem estar acessíveis nos caminhos especificados

## 🎯 Casos de Uso

- **Estudantes**: Organização de material de estudo por disciplina
- **Professores**: Gerenciamento de slides e material didático
- **Pesquisadores**: Organização de referências bibliográficas
- **Bibliotecas Digitais**: Catalogação e organização de acervo PDF

## 📝 Notas de Desenvolvimento

- Todos os documentos são copiados para a estrutura da biblioteca
- Os metadados são preservados através de serialização
- O sistema suporta múltiplas bibliotecas simultâneas
