# Projeto de OPE da Faculdade Impacta de Tecnologia

## Workflow

* Para começar, clone o repositório

```
git clone  https://github.com/gabrielsarti-impacta/ope.git
```

* Se não estiver na branch **Develop**, mude para ela

```
git checkout develop
```

* Para cada **Feature** nova, é necessário criar uma nova branch derivada da **Develop** seguindo a nomenclatura demonstrada:

```
git checkout -b feature/[nomeDoComponente]
git push origin feature/[nomeDoComponente]
```

* Para cada **Hotfix** realizado, é necessário criar uma nova branch derivada da **Develop** seguindo a nomenclatura demonstrada:

```
git checkout -b hotfix/[nomeDoComponente]
git push origin hotfix/[nomeDoComponente]
```

* Ao final do trabalho na branch de feature/hotfix, faça o merge para a branch **Develop**

```
git checkout develop
git merge feature/[nomeDoComponente]
git push origin develop

```



## Branchs Principais

### Master

Aqui se encontra o projeto testado e validado

### Develop

É aqui que deve ser feito todo o desenvolvimento

### Documentation

Todos os artefatos que compõem a documentação do projeto se encontram aqui


