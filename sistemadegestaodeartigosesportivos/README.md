# Classe `Endereco` - Resumo

A classe `Endereco` representa um endereço no sistema e é mapeada para uma tabela chamada `ENDERECOS` no banco de dados, utilizando JPA (Java Persistence API). Ela contém informações sobre a localização, como logradouro, bairro, cidade, UF (Unidade Federativa), CEP, número e complemento.

## Atributos

- `id`: Identificador único do endereço (gerado automaticamente pelo banco).
- `logradouro`, `bairro`, `cidade`: Campos obrigatórios que descrevem o endereço.
- `uf`: Unidade Federativa (estado), opcional.
- `cep`: Código de endereçamento postal, opcional.
- `numero`: Número do imóvel, opcional.
- `complemento`: Detalhes adicionais sobre o endereço, opcional.

## Validações

- `@NotNull` e `@NotEmpty` garantem que `logradouro`, `bairro` e `cidade` não sejam vazios.
- `@Entity`: Define a classe como uma entidade JPA.
- `@Table(name = "ENDERECOS")`: Define o nome da tabela no banco de dados.

## Métodos

A classe inclui métodos `get` e `set` para acessar e modificar seus atributos, como:

- `getLogradouro()` e `setLogradouro(String logradouro)`
- `getBairro()` e `setBairro(String bairro)`
- `getCidade()` e `setCidade(String cidade)`

## Exemplo de Uso

```java
Endereco endereco = new Endereco();
endereco.setLogradouro("Rua das Flores");
endereco.setBairro("Centro");
endereco.setCidade("São Paulo");
endereco.setUf(UF.SP);
endereco.setCep("01234-567");
endereco.setNumero(123);
```

A classe `Endereco` é usada para representar e persistir informações de localização de forma estruturada no banco de dados.

# Classe `Franquiado` - Descrição

A classe `Franquiado` representa a entidade de um franquiado (ou franqueado) no sistema de gestão de uma chocolateria. Ela é mapeada como uma entidade do banco de dados através da anotação `@Entity` e faz uso de JPA (Java Persistence API) para o gerenciamento de persistência.

## Atributos

- `id`: Identificador único do franquiado no sistema. É gerado automaticamente pelo banco de dados, com o tipo `long`.
  
- `nome`: Nome do franquiado. Este campo não pode ser em branco, como especificado pela anotação `@NotBlank`.

- `CNPJ`: Cadastro Nacional da Pessoa Jurídica do franquiado. Também não pode ser em branco, validado com `@NotBlank`.

- `endereco`: Endereço associado ao franquiado, representado pela entidade `Endereco`. A relação entre `Franquiado` e `Endereco` é mapeada com a anotação `@OneToOne`, indicando que cada franquiado possui um único endereço. A anotação `@Valid` indica que as validações para o endereço também são realizadas.

- `email`: Endereço de e-mail do franquiado. Este campo é opcional, mas pode ser utilizado para comunicação.

- `telefone`: Número de telefone do franquiado. Assim como o `nome` e `CNPJ`, este campo não pode ser vazio, sendo validado pela anotação `@NotBlank`.

## Anotações e Validações

- `@Entity`: Define a classe como uma entidade JPA, que será mapeada para uma tabela no banco de dados.
- `@Id`: Indica que o campo `id` é a chave primária da entidade.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Define que o valor de `id` será gerado automaticamente pelo banco de dados, utilizando a estratégia `IDENTITY`.
- `@NotBlank`: Validação que assegura que os campos `nome`, `CNPJ` e `telefone` não sejam vazios.
- `@OneToOne(cascade = CascadeType.ALL, optional = false)`: Define um relacionamento um-para-um entre `Franquiado` e `Endereco`, onde a operação de cascata (inclusão, atualização, exclusão) é propagada para o `Endereco`. O campo `optional = false` indica que um franquiado deve ter obrigatoriamente um endereço.
- `@JoinColumn(name = "endereco_id")`: Define a coluna `endereco_id` que será usada para fazer a associação entre `Franquiado` e `Endereco`.

# Classe `Endereco` - Resumo

## Métodos

A classe `Franquiado` segue o padrão de uso de getters e setters para acessar e modificar os atributos da entidade.

### Exemplos de Getters e Setters:

- `getId()` e `setId(long id)` para acessar e alterar o ID.
- `getNome()` e `setNome(String nome)` para acessar e alterar o nome do franquiado.
- `getCNPJ()` e `setCNPJ(String cnpj)` para acessar e alterar o CNPJ do franquiado.
- `getEndereco()` e `setEndereco(Endereco endereco)` para acessar e alterar o endereço associado ao franquiado.
- `getEmail()` e `setEmail(String email)` para acessar e alterar o e-mail do franquiado.
- `getTelefone()` e `setTelefone(String telefone)` para acessar e alterar o telefone do franquiado.

## Exemplo de Uso

```java
Franquiado franquiado = new Franquiado();
franquiado.setNome("Chocolateria XYZ");
franquiado.setCNPJ("12.345.678/0001-90");
franquiado.setTelefone("(11) 1234-5678");
franquiado.setEmail("contato@chocolaterianovosabor.com.br");

Endereco endereco = new Endereco();
endereco.setRua("Rua das Flores");
endereco.setNumero("123");
endereco.setCidade("São Paulo");
endereco.setEstado("SP");

franquiado.setEndereco(endereco);
```

Este exemplo cria um objeto `Franquiado`, define seus atributos e associa um `Endereco` a ele.

# Classe `Funcionario` - Descrição

A classe `Funcionario` representa um funcionário no sistema, com informações pessoais e de contato, além de um relacionamento com um endereço. Ela é mapeada como uma entidade JPA e contém validações para garantir que os dados sejam fornecidos corretamente.

## Atributos

- `id`: Identificador único do funcionário, gerado automaticamente pelo banco de dados.
  
- `nome`: Nome completo do funcionário. Este campo é obrigatório, validado com `@NotBlank`.

- `email`: Endereço de e-mail do funcionário. Também é um campo obrigatório, validado com `@NotBlank`.

- `telefone`: Número de telefone do funcionário, validado com `@NotBlank` para garantir que não esteja vazio.

- `cpf`: CPF do funcionário, validado com `@NotBlank` e formatado com a anotação `@CPF` (do Hibernate Validator) para garantir que o CPF tenha um formato válido.

- `rg`: Número do RG do funcionário, um campo obrigatório validado com `@NotBlank`.

- `idade`: Idade do funcionário, que deve ser maior ou igual a 1, validado com `@Min(value = 1)`.

- `endereco`: Relacionamento de um-para-um com a classe `Endereco`, representando o endereço do funcionário. O campo é obrigatório, e as operações de cascata (inclusão, atualização, exclusão) são aplicadas ao objeto `Endereco`.

## Anotações e Validações

- `@Entity`: Marca a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados.
  
- `@Id`: Indica que o campo `id` é a chave primária da entidade.

- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Define que o campo `id` será gerado automaticamente pelo banco de dados com a estratégia `IDENTITY`.

- `@Column(length = 1000, nullable = false)`: Define a coluna para o atributo `nome`, com um comprimento máximo de 1000 caracteres e não podendo ser nula.

- `@NotBlank`: Garante que os campos `nome`, `email`, `telefone`, `cpf`, e `rg` não sejam vazios.

- `@Min(value = 1)`: Garante que o campo `idade` seja maior ou igual a 1.

- `@OneToOne(cascade = CascadeType.ALL, optional = false)`: Define o relacionamento de um-para-um entre `Funcionario` e `Endereco`. O `cascade = CascadeType.ALL` significa que as operações realizadas no `Funcionario` serão propagadas para o `Endereco`, e `optional = false` garante que todo funcionário tenha um endereço associado.

- `@JoinColumn(name = "endereco_id")`: Define o nome da coluna `endereco_id` para o relacionamento com a tabela `Endereco`.

- `@Valid`: Garante que as validações da classe `Endereco` sejam aplicadas quando um endereço for associado ao funcionário.

- `@CPF`: Valida que o campo `cpf` tem o formato de um CPF válido.

## Métodos

A classe inclui métodos `get` e `set` para acessar e modificar seus atributos, como:

- `getNome()` e `setNome(String nome)` para acessar e alterar o nome do funcionário.
- `getEmail()` e `setEmail(String email)` para acessar e alterar o e-mail.
- `getTelefone()` e `setTelefone(String telefone)` para acessar e alterar o telefone.
- `getCpf()` e `setCpf(String cpf)` para acessar e alterar o CPF.
- `getRg()` e `setRg(String rg)` para acessar e alterar o RG.
- `getIdade()` e `setIdade(int idade)` para acessar e alterar a idade.
- `getEndereco()` e `setEndereco(Endereco endereco)` para acessar e alterar o endereço associado ao funcionário.

## Exemplo de Uso

```java
Funcionario funcionario = new Funcionario();
funcionario.setNome("João da Silva");
funcionario.setEmail("joao.silva@empresa.com");
funcionario.setTelefone("(11) 98765-4321");
funcionario.setCpf("123.456.789-00");
funcionario.setRg("12.345.678-9");
funcionario.setIdade(30);

Endereco endereco = new Endereco();
endereco.setLogradouro("Rua Exemplo");
endereco.setBairro("Centro");
endereco.setCidade("São Paulo");
funcionario.setEndereco(endereco);
```

Neste exemplo, um objeto `Funcionario` é criado e preenchido com dados, incluindo a associação com um objeto `Endereco`.

## Resumo

A classe `Funcionario` é usada para representar e persistir as informações de um funcionário no sistema. Ela garante que os campos obrigatórios sejam preenchidos corretamente e permite o gerenciamento do funcionário e seu endereço no banco de dados, utilizando as anotações do JPA para persistência e validação de dados.

# Classe `ItemEstoque` - Descrição

A classe `ItemEstoque` representa um item de estoque no sistema de gestão de artigos esportivos. Ela armazena a quantidade de um produto disponível no estoque para diferentes categorias: funcionário, franquiado e produto geral.

## Atributos

- `qtd_produto`: A quantidade do produto no estoque geral, ou seja, a quantidade total do produto disponível.
  
- `qtd_franquiado`: A quantidade do produto disponível especificamente para os franquiados.

- `qtd_funcionario`: A quantidade do produto destinada aos funcionários.

Esses três campos representam diferentes formas de distribuição do estoque, com foco na gestão separada por tipo de usuário (produto geral, franquiado, e funcionário).

## Implementação de `Serializable`

- A classe implementa a interface `Serializable`, o que permite que objetos da classe possam ser convertidos para um formato de byte-stream, essencial para salvar ou transmitir objetos de forma eficiente.

- O campo `serialVersionUID` é um identificador único para a versão da classe, utilizado para garantir a compatibilidade entre versões serializadas da classe.

## Métodos

### Getters e Setters

A classe inclui métodos `get` e `set` para acessar e modificar os atributos:

- `getQtd_produto()` e `setQtd_produto(long qtd_produto)` para acessar e alterar a quantidade de produto no estoque.
- `getQtd_franquiado()` e `setQtd_franquiado(long qtd_franquiado)` para acessar e alterar a quantidade disponível para franquiados.
- `getQtd_funcionario()` e `setQtd_funcionario(long qtd_funcionario)` para acessar e alterar a quantidade destinada aos funcionários.

### Método `equals()`

O método `equals()` é sobrescrito para comparar dois objetos `ItemEstoque` e determinar se eles são iguais. Ele faz isso verificando se os valores de `qtd_produto`, `qtd_franquiado`, e `qtd_funcionario` são idênticos entre os dois objetos. 

- Se os valores dessas propriedades coincidirem entre dois objetos `ItemEstoque`, o método retornará `true`, indicando que os objetos são iguais.
- Caso contrário, retornará `false`.

## Exemplo de Uso

```java
ItemEstoque item1 = new ItemEstoque();
item1.setQtd_produto(100);
item1.setQtd_franquiado(50);
item1.setQtd_funcionario(30);

ItemEstoque item2 = new ItemEstoque();
item2.setQtd_produto(100);
item2.setQtd_franquiado(50);
item2.setQtd_funcionario(30);

boolean saoIguais = item1.equals(item2);  // Retorna true, pois os valores são iguais.
```

Neste exemplo, dois objetos `ItemEstoque` são criados e seus atributos são configurados. O método `equals()` é utilizado para verificar se os dois objetos possuem as mesmas quantidades em todos os campos.

## Resumo

A classe `ItemEstoque` é usada para representar os itens no estoque, divididos por categorias: produto geral, franquiado e funcionário. Ela fornece métodos para acessar e modificar as quantidades, além de um método `equals()` para comparação de objetos. Além disso, a classe é serializável, permitindo a manipulação eficiente dos dados para armazenamento ou transmissão.

# Classe `ItemVenda` - Descrição

A classe `ItemVenda` representa um item individual em uma venda de produtos. Ela armazena informações sobre a quantidade do produto, o valor de venda unitário, e o produto associado à venda. Além disso, a classe calcula o valor total da venda para esse item.

## Atributos

- `id`: Identificador único do item de venda, gerado automaticamente pelo banco de dados.

- `quantidade`: A quantidade do produto que está sendo vendida. Este campo é obrigatório, e a quantidade deve ser maior ou igual a 1, validado pela anotação `@Min`.

- `valorVenda`: O valor unitário de venda do produto. Este campo tem um valor mínimo de 1, validado pela anotação `@DecimalMin`. O valor padrão é `0d`, o que indica que, inicialmente, o valor de venda pode ser zero, mas deve ser ajustado ao realizar a venda.

- `produto`: Relacionamento com o produto vendido. A anotação `@ManyToOne` indica que múltiplos itens de venda podem estar associados ao mesmo produto. O `cascade = CascadeType.REFRESH` garante que qualquer atualização no produto seja refletida automaticamente no item de venda.

## Métodos

### Getters e Setters

A classe inclui os seguintes métodos para acessar e modificar seus atributos:

- `getId()` e `setId(long id)` para acessar e alterar o identificador do item de venda.
- `getQuantidade()` e `setQuantidade(int quantidade)` para acessar e alterar a quantidade de unidades do produto.
- `getValorVenda()` e `setValorVenda(Double valorVenda)` para acessar e alterar o valor unitário do produto na venda.
- `getProduto()` e `setProduto(Produto produto)` para acessar e associar um produto ao item de venda.

### Método `getValorFinal()`

O método `getValorFinal()` calcula o valor total do item de venda multiplicando o valor unitário (`valorVenda`) pela quantidade (`quantidade`). Esse valor representa o total a ser pago por esse item específico da venda.

```java
public Double getValorFinal() {
    return this.getValorVenda() * this.getQuantidade();
}
```

## Exemplo de Uso

```java
Produto produto = new Produto();
produto.setNome("Tênis Esportivo");
produto.setPreco(100.0);

ItemVenda itemVenda = new ItemVenda();
itemVenda.setQuantidade(2);
itemVenda.setValorVenda(120.0);
itemVenda.setProduto(produto);

Double valorTotal = itemVenda.getValorFinal();  // 240.0 (120.0 * 2)
```

Neste exemplo, um produto é criado e associado a um `ItemVenda`, onde o valor unitário de venda é definido como `120.0` e a quantidade é `2`. O método `getValorFinal()` calcula o valor total da venda, que será `240.0`.

## Resumo

A classe `ItemVenda` é usada para representar um item dentro de uma venda. Ela armazena informações sobre a quantidade do produto, o valor unitário de venda, e o produto relacionado. Além disso, possui um método que calcula o valor total do item de venda com base na quantidade e no valor unitário. A classe também faz uso de anotações de validação para garantir que os dados sejam corretos, como garantir que a quantidade seja positiva e o valor de venda tenha um mínimo de 1.

# Classe `Produto` - Descrição

A classe `Produto` representa um produto no sistema de gestão de artigos esportivos. Ela armazena informações sobre a marca, tipo e valor do produto, que são utilizados para catalogar e gerenciar os produtos disponíveis para venda.

## Atributos

- `id`: Identificador único do produto, gerado automaticamente pelo banco de dados. Este campo é a chave primária da tabela.
  
- `marca`: A marca do produto. Este campo é obrigatório, validado pela anotação `@NotBlank`, que garante que não pode ser vazio. A coluna no banco de dados permite até 2000 caracteres.

- `tipo`: O tipo ou categoria do produto (por exemplo, "Tênis", "Bola", etc.). Também é um campo obrigatório, validado com `@NotBlank`, para garantir que o tipo do produto seja informado. A coluna no banco de dados permite até 2000 caracteres.

- `valor`: O valor de venda do produto. Este campo é obrigatório e deve ser no mínimo 1, conforme a validação `@DecimalMin(value = "1")`. Caso o valor não seja especificado, o valor padrão é `0d`. A anotação `@NotNull` também garante que o valor seja informado.

## Anotações e Validações

- `@Entity`: Marca a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados.

- `@Id`: Indica que o campo `id` é a chave primária da entidade.

- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Define que o campo `id` será gerado automaticamente pelo banco de dados, com o comportamento de incremento.

- `@Column(length = 2000)`: Define o comprimento máximo da coluna para os atributos `marca` e `tipo`, permitindo até 2000 caracteres no banco de dados.

- `@NotBlank`: Garante que os campos `marca` e `tipo` não sejam nulos ou vazios.

- `@DecimalMin(value = "1", message = "Valor mínimo é um")`: Garante que o campo `valor` tenha um valor mínimo de 1.0.

- `@NotNull`: Garante que o campo `valor` seja informado (não pode ser nulo).

## Métodos

### Getters e Setters

A classe inclui os seguintes métodos para acessar e modificar seus atributos:

- `getId()` e `setId(long id)` para acessar e alterar o identificador único do produto.
- `getMarca()` e `setMarca(String marca)` para acessar e alterar a marca do produto.
- `getTipo()` e `setTipo(String tipo)` para acessar e alterar o tipo do produto.
- `getValor()` e `setValor(Double valor)` para acessar e alterar o valor do produto.

## Exemplo de Uso

```java
Produto produto = new Produto();
produto.setMarca("Nike");
produto.setTipo("Tênis");
produto.setValor(299.99);
```

Neste exemplo, um novo produto é criado e seus atributos `marca`, `tipo` e `valor` são configurados. O produto tem a marca "Nike", é do tipo "Tênis", e tem um valor de venda de 299,99.

## Resumo

A classe `Produto` é usada para representar e persistir informações sobre os produtos no sistema de gestão de artigos esportivos. Ela inclui atributos como marca, tipo e valor de venda, e utiliza anotações de validação para garantir que os dados inseridos sejam válidos (como valores mínimos e campos obrigatórios). Além disso, a classe é mapeada para uma tabela no banco de dados utilizando JPA.

# Enum `UF` - Descrição

A classe `UF` é um **enum** (tipo enumerado) que representa os estados do Brasil, mapeando a sigla e o nome completo de cada estado. O uso de um **enum** permite que cada valor da enumeração seja tratado de forma segura e com comportamento predefinido, garantindo consistência ao trabalhar com os estados em um sistema.

## Atributos

- `id`: O identificador único de cada estado. Este campo é gerado automaticamente pelo banco de dados usando a estratégia `GenerationType.IDENTITY`.

- `sigla`: A sigla de cada estado, como "SP" para São Paulo ou "RJ" para Rio de Janeiro. Representa a abreviação oficial de cada unidade federativa.

- `descricao`: O nome completo do estado, como "São Paulo", "Minas Gerais", etc.

## Construtor

O **construtor** da enumeração `UF` recebe dois parâmetros: a sigla e a descrição do estado. Ele é utilizado para inicializar os valores de cada constante do tipo enum, como "AC" para "Acre", "RJ" para "Rio de Janeiro", etc.

```java
UF(String sigla, String descricao) {
    this.sigla = sigla;
    this.descricao = descricao;
}
```

## Métodos

### `getSigla()`

Retorna a sigla do estado. Por exemplo, para "São Paulo" a sigla retornada será "SP".

```java
public String getSigla() {
    return sigla;
}
```

### `getDescricao()`

Retorna a descrição do estado, ou seja, o nome completo. Para "SP" o retorno seria "São Paulo".

```java
public String getDescricao() {
    return descricao;
}
```

### `toString()`

Sobrescreve o método `toString()` para que, ao imprimir uma instância de `UF`, a sigla do estado seja retornada como uma string. Por exemplo, `UF.SP.toString()` retornaria `"SP"`.

```java
@Override
public String toString() {
    return getSigla();
}
```

## Exemplo de Uso

Aqui está um exemplo simples de como utilizar o enum `UF` em um sistema:

```java
public class ExemploUF {
    public static void main(String[] args) {
        // Acessando uma constante do enum
        UF estado = UF.SP;

        // Obtendo a sigla e a descrição do estado
        System.out.println("Sigla: " + estado.getSigla());  // Saída: "Sigla: SP"
        System.out.println("Descrição: " + estado.getDescricao());  // Saída: "Descrição: São Paulo"

        // Usando o toString
        System.out.println("Estado: " + estado);  // Saída: "Estado: SP"
    }
}
```

No exemplo acima, é possível acessar a sigla e a descrição de um estado como `UF.SP`, que corresponde a "São Paulo". O método `toString()` é utilizado para imprimir a sigla do estado.

## Resumo

A classe `UF` é uma enumeração que representa os estados do Brasil, onde cada valor é composto por uma sigla e uma descrição (nome completo do estado). É usada para garantir a integridade e consistência ao manipular estados em um sistema, utilizando métodos que retornam tanto a sigla quanto a descrição do estado, além de sobrescrever o `toString()` para fornecer a sigla como string.

Esta enumeração pode ser útil em sistemas que precisam armazenar, exibir ou validar informações relacionadas aos estados brasileiros.

# Classe `Venda` - Descrição

A classe `Venda` representa uma venda realizada no sistema de chocolates, armazenando informações sobre a data da venda, os itens vendidos e o comprador (um Franqueado). Esta classe é usada para controlar o histórico de transações de vendas, permitindo associar cada venda a um conjunto de itens e a um franqueado específico.

## Atributos

- **`id`**: Identificador único da venda, gerado automaticamente pelo banco de dados.

- **`data`**: A data da venda, representada por um objeto `Date`. A anotação `@Temporal(TemporalType.DATE)` indica que apenas a data (sem a hora) será armazenada. O formato de data é configurado com a anotação `@DateTimeFormat(pattern = "yyyy-MM-dd")` para garantir que a data seja formatada corretamente ao ser exibida ou editada.

- **`listaItens`**: Lista de itens vendidos na venda. Cada item é representado pela classe `ItemVenda`. A anotação `@OneToMany` estabelece um relacionamento de um para muitos, onde uma venda pode ter vários itens. O `CascadeType.ALL` garante que qualquer operação (como persistir ou excluir) realizada na venda seja refletida nos itens. A associação com a tabela de itens é feita pela chave estrangeira `venda_id`.

- **`comprador`**: Relacionamento com a entidade `Franquiado` (representando o franqueado comprador). A anotação `@ManyToOne` indica que várias vendas podem ser feitas por um único franqueado. O `CascadeType.MERGE` e `CascadeType.REFRESH` permitem que o franqueado seja atualizado ou sincronizado automaticamente com a venda, caso haja alterações.

## Anotações e Relacionamentos

- **`@Entity`**: Marca a classe como uma entidade JPA, o que significa que ela será mapeada para uma tabela no banco de dados.

- **`@Id`**: Define o campo `id` como a chave primária da tabela.

- **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: Indica que o valor do campo `id` será gerado automaticamente pelo banco de dados.

- **`@Temporal(TemporalType.DATE)`**: A data da venda é armazenada apenas como uma data, sem a parte da hora.

- **`@OneToMany(cascade = CascadeType.ALL)`**: Define um relacionamento de um para muitos com a entidade `ItemVenda`, onde cada venda pode ter vários itens. O `cascade = CascadeType.ALL` propaga qualquer operação de persistência (como salvar ou excluir) aos itens relacionados.

- **`@JoinColumn(name = "venda_id")`**: Especifica a chave estrangeira que será usada para associar os itens da venda à venda específica. O nome da coluna será `venda_id`.

- **`@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })`**: Define que a venda está associada a um único `Franquiado` (o comprador) e que qualquer atualização ou sincronização do franqueado será propagada para a venda.

## Métodos

### Getters e Setters

A classe possui os seguintes métodos para acessar e modificar seus atributos:

- **`getId()`** e **`setId(long id)`**: Para acessar e definir o identificador único da venda.
- **`getData()`** e **`setData(Date data)`**: Para acessar e definir a data da venda.
- **`getListaItens()`** e **`setListaItens(List<ItemVenda> listaItens)`**: Para acessar e modificar a lista de itens vendidos nesta venda.
- **`getComprador()`** e **`setComprador(Franquiado comprador)`**: Para acessar e definir o comprador (franqueado) associado a esta venda.

## Exemplo de Uso

Aqui está um exemplo de como usar a classe `Venda`:

```java
import java.util.ArrayList;
import java.util.Date;

public class ExemploVenda {
    public static void main(String[] args) {
        // Criando um franqueado (comprador)
        Franquiado comprador = new Franquiado();
        comprador.setNome("Chocolates do Brasil");
        
        // Criando uma venda
        Venda venda = new Venda();
        venda.setData(new Date());  // Definindo a data da venda
        venda.setComprador(comprador);  // Associando o comprador

        // Criando itens da venda
        ItemVenda item1 = new ItemVenda();
        item1.setQuantidade(2);
        item1.setValorVenda(15.00);
        
        ItemVenda item2 = new ItemVenda();
        item2.setQuantidade(3);
        item2.setValorVenda(10.00);

        // Adicionando itens à venda
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);
        venda.setListaItens(itens);  // Associando os itens à venda

        // Exibindo informações da venda
        System.out.println("Venda realizada em: " + venda.getData());
        System.out.println("Comprador: " + venda.getComprador().getNome());
        for (ItemVenda item : venda.getListaItens()) {
            System.out.println("Item: " + item.getProduto().getNome() + " | Quantidade: " + item.getQuantidade() + " | Valor unitário: " + item.getValorVenda());
        }
    }
}
```

### Explicação do Exemplo

1. **Criação do Franqueado**: O franqueado (comprador) é criado e seu nome é configurado.
2. **Criação da Venda**: A venda é criada, com a data sendo definida para o momento atual e o comprador associado.
3. **Criação dos Itens**: Dois itens de venda são criados e configurados com quantidade e valor unitário.
4. **Associação dos Itens à Venda**: Os itens são adicionados a uma lista e essa lista é associada à venda.
5. **Exibição da Venda**: As informações da venda, incluindo a data, comprador e itens, são exibidas.

## Resumo

A classe `Venda` é usada para representar uma transação de venda no sistema. Ela mantém informações como a data da venda, os itens vendidos e o comprador (franqueado). Ela faz uso de anotações JPA para definir os relacionamentos com outras entidades, como `ItemVenda` e `Franquiado`, além de garantir que os dados sejam corretamente persistidos no banco de dados. A classe também proporciona métodos para acessar e modificar suas propriedades, facilitando a manipulação das informações de vendas no sistema.