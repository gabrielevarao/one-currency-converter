---
<p align="center"> <b> <sup>ONE - Oracle Next Education | Alura</sup></b></p> 
<h1 align="center">Currency Converter</h1>

<div align="center">
  
![Static Badge](https://img.shields.io/badge/java-%236A5ACD?style=flat-square) 
![Static Badge](https://img.shields.io/badge/linha_de_comando-%232E8B57?style=flat-square)
![Static Badge](https://img.shields.io/badge/api_consumer-%232F4F4F%09?style=flat-square)
![Static Badge](https://img.shields.io/badge/no_key_required-red?style=flat-square)

</div>

Projeto de conclusão da _Formação Java e Orientação a Objetos_.
Nesta aplicação, o usuário informa uma quantia numa moeda e recebe o valor correspondente numa outra moeda de sua escolha.

## 🔄 Fluxo do usuário
↳ Digita o código da moeda a ser convertida (moeda base).\
↳ Digita o código da moeda para a qual a quantia deve ser convertida (moeda destino).\
↳ Digita a quantia a ser convertida.\
↳ Escolhe se deseja fazer uma nova consulta ou não.
> A qualquer momento o usuário pode `solicitar a lista das moedas` disponíveis ou `sair` da aplicação.

## 🚥 Validações
#### ⚠️Código inválido
Caso o usuário insira um código inválido - formato errado ou código inexistente -, uma mensagem de erro aparece e o usuário tem a chance de inserir novamente um código. Esse ciclo somente se encerra quando um código válido é enviado ou quando o usuário digita "sair".
> Para evitar problemas case-sensitive, todo código é previamente formatado para maiúsculas. 
#### ⚠️Formato de número inválido
Caso o usuário informe a quantia enviando um código alfanumérico ou quaisquer outras entradas que não correspondam a um número decimal, uma mensagem de erro é enviada e o usuário tem a chance de inserir novamente a quantia. Esse ciclo somente se encerra quando uma quantia válida é enviada ou quando o usuário digita "sair".
> Para evitar problemas de formato de moeda - 100,00 ou 100.00, por exemplo -, os valores são previamente tratados para o formato correto.


## 🔗 Tecnologias utilizadas
- `APIs` [HexaRate API](https://hexarate.paikama.co/) para obter a taxa de câmbio moeda-moeda (no key). [Gist - gp187](https://gist.github.com/gp187/4393cbc6dd761225071270c29b341b7b) para acessar a lista de moedas com base na norma ISO 4217.
- Biblioteca `Gson` para a conversão JSON→Objeto Java.
- `Records` para a transferência de dados.
- Interface `Type` para viabilizar a desserialização de `List<String>`.
- Interface funcional `Predicate` para estabelecer a regra da validação do código das moedas.
- `Stream` para iterar a lista de códigos, efetivamente performando a validação com base no predicate.
- `Scanner` para interação usuário-aplicação.

## 📚 Contexto
Este projeto é um dos desafios obrigatórios das formações do Programa ONE (Oracle Next Education) em parceria com a Alura.

---
