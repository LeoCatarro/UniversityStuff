from sklearn.model_selection import train_test_split
import numpy
import math
from classes import *

#Apagar import!
import sys

#
#Variáveis globais auxiliares
#
attribute_list = []     #Lista para os atributos
class_list = []         #Lista para as classes

#
# Carregamento e Leitura dos Dados apartir do ficheiro
#
data = numpy.genfromtxt("soybean.csv", delimiter=",", dtype=None, encoding=None)
xdata = data[1:,0:-1]     #dados: da segunda à ultima linha, da primeira à penúltima coluna
ydata = data[1:,-1]       #classe: da segunda à ultima linha, só última coluna
total = len(data)-1 
#Dimensoes da base de dados
rows = len(data)    
cols = len(data[0])


#   Classe que representa a árvore de Decisão
class DecisionTreeREPrune:
    def __init__(self, criterion,prune):
        self.criterion = criterion
        self.prune = prune
    
    def fit(self, xdata, ydata):        
        #
        #       Tratamento dos dados de input
        #
        for i in data[0,:-1]:                                         #Criacao dos atributos
            attribute = AttributeData(i, 0)
            attribute_list.append(attribute)

            for j in data[1:, attribute_list.index(attribute)]:       #Craicao dos tipos para cada atributo criado
                
                attribute_type = TypeData(j, 1, 0, False)

                if attribute_type not in attribute_list[attribute_list.index(attribute)].type_list:
                    attribute_list[attribute_list.index(attribute)].type_list.append(attribute_type)

                else:
                    index_attribute = attribute_list.index(attribute)
                    index_type = attribute_list[index_attribute].type_list.index(attribute_type)
                    attribute_list[index_attribute].type_list[index_type].n_times += 1

        put_classes_in_list(ydata)
        initialize_all_class_list()

        #Ocorrencia de cada valor de atributo por classe
        for j in range(cols-1):
            for i in range(1,rows):
                index = find_type_index_in_attribute_type_list(attribute_list[j].type_list, data[i, j])
                #index_of_class = class_list.index(data[i, -1])
                tmp = attribute_list[j].type_list[index]
                tmp.class_list[class_list.index(data[i, -1])].class_ocurrence += 1

                class_object = Class(class_list[class_list.index(data[i,-1])], tmp.class_list[class_list.index(data[i, -1])].class_ocurrence)
            
                if len(tmp.class_list) == 0:
                    tmp.class_list.append(class_object)

                for element in tmp.class_list:
                    if class_object in tmp.class_list:
                        tmp.class_list[tmp.class_list.index(class_object)] = class_object
                    else:
                        tmp.class_list.append(class_object)

        #Cálculo da entropia para todos os tipos de atributos
        for element in attribute_list:
            impurity_per_type(attribute_list[attribute_list.index(element)].type_list, self.criterion)       

        #Cálculo da entropia para cada atributo
        if self.criterion == "entropia":
            attributes_impurity(self.criterion)
            build_tree(self)        #Constrói a árvore e printa os nodes nela presentes

        elif self.criterion == "gini":
            attributes_impurity(self.criterion)
            build_tree(self)        #Constrói a árvore e printa os nodes nela presentes

        elif self.criterion == "erro":
            attributes_impurity(self.criterion)
            build_tree(self)        #Constrói a árvore e printa os nodes nela presentes

        else:
            print("Criterion should be: entropia, gini or erro. Not" + self.criterion)
            sys.exit()
        
    def score(self, xdata, ydata):
        #Not implemented!
        return sys.exit()


#Procura o index do tipo de um atributo na lista de tipos desse atributo em especifico
def find_type_index_in_attribute_type_list(type_list, type): 
    for i in type_list:
        if i.type_name == type:
            return type_list.index(i)

#Procura todas as classes existentes e coloca-as na lista class_list
def put_classes_in_list(ydata):
    for i in ydata[0:]:
        if i not in class_list:
            class_list.append(i)

#Inicializa a class_list
def initialize_all_class_list():
    for i in attribute_list:
        for j in i.type_list:
            for k in range(0, len(class_list)):
                class_object = Class(class_list[k],0)
                j.class_list.append(class_object)

#Numero de ocorrencias total por classe
def class_ocurrence(class_list):
    ocorrences = []

    for i in range(len(class_list)):
        ocorrences.append(0)

    for i in range(rows):
        if data[i, -1] in class_list:
            ocorrences[class_list.index(data[i, -1])] += 1

    return ocorrences    

#Cálculo da entropia por tipo
def impurity_per_type(type_list, criterion):
    if criterion == "entropia":   
        for element in type_list:
            for i in range(len(element.class_list)):
                if element.n_times != 0:
                    fraction = element.class_list[i].class_ocurrence / element.n_times
                    if fraction == 0:
                        type_list[type_list.index(element)].entropy = 0 
                    else: 
                        type_list[type_list.index(element)].entropy -= fraction * math.log(fraction, 2)
        return type_list        

    if criterion == "gini":
        for element_type in type_list:
            for ocurrence in element_type.class_list:
                if element_type.n_times == 0:
                    element_type.entropy = 0
                else:
                    element_type.entropy *= (ocurrence.class_ocurrence/element_type.n_times)
            element_type.entropy = -element_type.entropy
        return type_list

    if criterion == "erro":
        #Descobrir o ocurrencias da classe maioritaria(classe com mais ocorrencias)
        #Fazer: total - ocurrencias/total
        #Not Implemented yet!
        for element_type in type_list:
            maximum = 0
            #Verifica qual a classe maioritaria
            for ocurrence in element_type.class_list:
                if ocurrence.class_ocurrence > maximum:
                    maximum = ocurrence.class_ocurrence
            element_type.entropy = total - (maximum/total)
        return type_list
    
#Cálculo da entropia por atributo
def attributes_impurity(criterion):
    if criterion == "entropia":
        for element in attribute_list:
            if element.flag == False:
                for i in range(len(element.type_list)):
                    element.attribute_entropy += (element.type_list[i].n_times/total) * element.type_list[i].entropy

    if criterion == "gini":
        for element in attribute_list:
            if element.flag == False:
                for element_type in element.type_list:
                    element_type.entropy = (element_type.n_times/total) * 2
                    for ocurrence in element_type.class_list:
                        element_type.entropy *= (ocurrence.class_ocurrence/element_type.n_times)
                    element.attribute_entropy += element_type.entropy
                element.attribute_entropy = 1 - element.attribute_entropy 
                element.attribute_entropy = -element.attribute_entropy
    
    if criterion == "erro":
        for element in attribute_list:
            if element.flag == False:
                for element_type in element.type_list:
                    error = 0
                    maximum = 0
                    for ocurrence in element_type.class_list:
                        if ocurrence.class_ocurrence > maximum:
                            maximum = ocurrence.class_ocurrence
                    for i in element_type.class_list:
                        if i.class_ocurrence != maximum:
                            error += i.class_ocurrence             
                    element_type.entropy = 1 - (error/total)
                    #print(element_type, element_type.)    
                element.attribute_entropy += element_type.entropy 

#Cálculo da entropia por atributo
#FAZER PARA GINI?
def update_attributes_impurity(element, criterion):
    #for element in attribute_list:  
    if criterion == "entropia":
        if element.flag == False and element.total != 0:
            for i in range(len(element.type_list)):
                element.attribute_entropy += (element.type_list[i].n_times/element.total) * element.type_list[i].entropy

    if criterion == "gini":
        if element.flag == False and element.total != 0:
            for i in range(len(element.type_list)):
                element.attribute_entropy = element.type_list[i].entropy
            element.attribute_entropy = -element.attribute_entropy

    if criterion == "erro":
        if element.flag == False and element.total != 0:
            for i in range(len(element.type_list)):
                element.attribute_entropy += element.type_list[i].entropy
            element.attribute_entropy = element.attribute_entropy

#Compara as impurezas calculada, retornando o atributo com menor impureza
def calc_min(element):

    aux = None

    for i in attribute_list:
        if i.is_node == False and element.is_node == False and element != i:
            if element.attribute_entropy <= i.attribute_entropy:
                aux = element   
    return aux

#Saber atributo apartir do valor da impureza(gini, entropia)
def search_attribute_from_impurity_value(attribute_list, impurity_value):
    for i in attribute_list:
        if i.attribute_entropy == impurity_value:
            return i    
    return None

#Verificar passado um atributo, se algum do seus tipos é homogenio
def is_homogeneous(attribute, new_node, tree_nodes):
    count = 0
    aux = 0
    result = []
    decision_list = []

    for i in range(len(new_node.decision_list)):
        decision_list.append(new_node.decision_list[i].type_name)
    
    node = Node(None, None)

    #
    #       Homogen Nodes in root
    #
    if new_node.is_root == True:
        for element in attribute.type_list:
            for i in range(len(element.class_list)):
                if element.class_list[i].class_ocurrence == 0:
                    count +=1  
                else:
                    aux = i    
            if(count == 1):
                element.is_homogeneity = True
            count = 0
        
            if element.is_homogeneity == True:
                name = element.class_list[aux]
                decision = element
                node = Node(name, decision)
                node.decision_list.append(decision)
                node.is_leaf = True
                result.append(node)
    
    #
    #       Remaining Nodes
    #
    else:
        for element in attribute.type_list:
            count = 0
            line_index = []

            for i in range(len(element.class_list)):
                if element.class_list[i].class_ocurrence == 0:
                    count +=1  
                else:
                    aux = i

            #if node.decision in decision_list:
            if len(decision_list) > len(new_node.decision_list):
                decision_list.remove(decision_list[-1])
            if element.is_homogeneity == False:
                decision_list.append(element.type_name)


            for i in range(1,rows):
                if set(decision_list).issubset(data[i]):
                    line_index.append(i)

            for i in line_index:
                classe = data[i, -1]  
                if count == 1:
                    element.is_homogeneity = True             
                    name = element.class_list[aux]
                    decision = element
                    node = Node(name, decision)
                    
                    node.decision_list.append(decision)
                    node.is_leaf = True
                    if node.key not in result:
                        result.append(node)       
                
                else:
                    name = element.class_list[class_list.index(classe)]
                    decision = element
                
                    if name not in new_node.childs:
                        node = Node(name, decision)
                        node.decision_list.append(decision)
                        new_node.childs.append(node)
                        node.is_leaf = True
                        #result.append(node)
                    
                    new_node.childs[new_node.childs.index(name)].key.class_ocurrence +=1
                    #result[result.index(name)].key.class_ocurrence += 1

    #TESTE: POSSIVEL TER QUE TIRAR!!!! MARTELADA!
    new_node.childs = list(set(new_node.childs))

    if(len(result) != 0):
        return list(dict.fromkeys(result))

    return []

#resets every ocurrence of a giventype
def reset_all_ocurrences(attribute):   

    for i in attribute.type_list:
        for j in i.class_list:
            j.class_ocurrence = 0

#resets every entropy of a given type
def reset_all_impurity(attribute):   

    for i in attribute_list:
        if i.flag == False:
            i.attribute_entropy = 0

    for i in attribute.type_list:
        i.entropy = 0

#Reseta as flags dos atributos, caso ainda nao sejam nós da árvore
def reset_all_flags():

    for i in attribute_list:
        if i.is_node == False:
            i.flag = False        

#Updates the class_list values after each recursion
def update_class_list(attribute, decision_list): 
    new_list = []
    aux = []
    
    for i in range(len(decision_list)):
        new_list.append(None)
        aux.append(decision_list[i].type_name)

    for element in attribute.type_list: #(HOT, MILD, COLD)
        attribute.total = 0

        line_index = []

        for i in range(len(new_list)):
            new_list[i] = None

        for i in range(len(decision_list)):
            new_list[i] = decision_list[i].type_name

        for i in new_list:
            if element.type_name != i:
                new_list.append(element.type_name)

        for i in new_list:
            if i == None:
                new_list.remove(i)

        if(len(new_list) == 0):
            return

        for i in range(1,rows):
            if set(new_list).issubset(data[i]):
                line_index.append(i)
            if set(aux).issubset(data[i]):
                attribute.total += 1  

        for i in line_index:
            class_name = data[i, -1]
            
            for j in element.class_list:
                if j.class_name == class_name:
                    j.class_ocurrence += 1
        element.n_times = len(line_index)    

#Calculate best type to be the decision for the next node
def best_split(node, criterion):
    min_entropy = TypeData(None, None, None, None)

    #calc entropy for every type of the node we are in
    impurity_per_type(node.key.type_list, criterion)
    if len(node.key.type_list) != 0: 
        min_entropy = node.key.type_list[0]

    for i in node.key.type_list:
        if (i.entropy < min_entropy.entropy) and i.is_homogeneity == False:
            min_entropy = i
            min_entropy.flag = True
    return min_entropy

#Recursive function to iterate tree
def recursive_split(node, tree_nodes, criterion):
    splits = []     #Lista de tipos do atributo do no onde estamos
    decision_list = []
    min_value = AttributeData(None, None)
    count = False
    
    if len(tree_nodes) < len(attribute_list) and node.key != None:
        #
        #   Calculos comuns(tanto para Root como para restantes Nodes)
        #

        #print(node, node.childs)
        #Possiveis splits para o nó atual
        for element in node.key.type_list:
            if element.flag == False:
                splits.append(element)

        #Verifica possiveis splits e filhos homogeneos
        #Adiciona-os á lista de filhos do nó atual
        node_list = is_homogeneous(node.key, node, tree_nodes)
        #print(node, tree_nodes)
        #Caso existam filhos homogeneos
        if len(node_list) != 0:
            for homo_child in node_list:
                for element in homo_child.decision_list:
                    element.flag = True
                    #homo_child.is_leaf = True
                    node.childs.append(homo_child)
        else: #node not in tree_nodes:
            #tree_nodes.append(node)
            for element in tree_nodes:
                if element.key == node.key:
                    count = True
            if count == True:
                count = False
            else:
                tree_nodes.append(node)           


        #print(node, node.childs)    
        
        #
        #       CASO SEJA FOLHA
        #

        #Verifica se nó atual, excepto para a root, se apenas foram encontradas folhas
        #o que significa que para esse ramo seguido, não há mais informação a adicionar
        if node.is_root == False:
            if all(child.is_leaf for child in node.childs):
                #A recursão atingiu so folhas para o nó onde estamos
                #Ver que atributos estão disponiveis para usar como nós
                #Refazer processo
                
                reset_all_flags()

                for element in attribute_list:
                    if element.flag == False:
                        split_type = best_split(node, criterion)
                        
                        reset_all_ocurrences(element)
                        reset_all_impurity(element)
                        update_class_list(element, decision_list)
                        impurity_per_type(element.type_list, criterion)
                        
                        for i in attribute_list:
                            if i.is_node == False:
                                update_attributes_impurity(i, criterion)
                            
                        aux = calc_min(element)
                        if aux is not None:
                            min_value = aux  
                        element.flag = True
                
                node = tree_nodes[-2]
                splits.clear()

                for element in node.key.type_list:
                    if element not in node.decision_list and element.flag == False:
                        splits.append(element)

                reset_all_impurity(node.key)
                reset_all_flags()

                for element in attribute_list:
                    if element.is_node == False:
                        split_type = best_split(node, criterion)
                        
                        reset_all_ocurrences(element)
                        reset_all_impurity(element)
                        update_class_list(element, decision_list)
                        impurity_per_type(element.type_list, criterion)
                        for i in attribute_list:
                            if i.is_node == False:
                                update_attributes_impurity(i, criterion)

                        aux = calc_min(element)
                        if aux is not None:
                            min_value = aux  
                        element.flag = True
                
                min_value.is_node = True
                new_node = Node(aux, splits)
                new_node.decision_list = splits

                if new_node not in node.childs:
                    node.childs.append(new_node)

                #node.childs.append(new_node)

                recursive_split(new_node, tree_nodes, criterion)
            else:
                print("test")
                for i in node.childs:
                    #print(i, node, i.is_leaf)
                    if i.is_leaf == False:
                        recursive_split(i, tree_nodes, criterion)    
        #
        #       CASO SEJA RAIZ
        #
        else:
            #Remove dos splits os ramos ja corridos, que constam da node_list
            for element in node.key.type_list:
                if element.flag == True and len(splits) != 0:
                    splits.remove(element)

            split_type = best_split(node, criterion)
            split_type.flag = True

            decision_list.append(split_type)

            for element in attribute_list:
                if element.is_node == False:
                    reset_all_ocurrences(element)
                    reset_all_impurity(element)
                    update_class_list(element, decision_list)
                    impurity_per_type(element.type_list, criterion)
                    
                    for i in attribute_list:
                        if i.is_node == False:
                            update_attributes_impurity(i, criterion)
                        
                    aux = calc_min(element)
                    if aux is not None:
                        min_value = aux  
                    element.flag = True
                    
            min_value.is_node = True
            new_node = Node(min_value, split_type)
            new_node.decision_list = decision_list

            if len(splits) != 0 and split_type in splits:
                node.childs.append(new_node)
            
            if split_type in splits:
                splits.remove(split_type)

            tree_nodes.append(new_node)
        
            recursive_split(new_node, tree_nodes, criterion)
            
    else:
        return
  
#Escolha da raiz de acordo com a menor impureza
def choose_root():
    aux = attribute_list[0].attribute_entropy
    for i in attribute_list:
        if aux > i.attribute_entropy:
            aux = i.attribute_entropy

    return search_attribute_from_impurity_value(attribute_list, aux)

#Build Tree Function
def build_tree(decision_tree):
    tree_nodes = []                         #Lista que guarda todos os nos que constam na árvore
    root = Node(choose_root(), None)        #escolha da raiz
    root.key.flag = True
    root.key.is_node = True
    root.is_root = True
    
    tree_nodes.append(root)
    recursive_split(root, tree_nodes, decision_tree.criterion)  
    print_tree(decision_tree, tree_nodes)

    return root

#Função que printa os nós na árvore
def print_tree(decision_tree, tree_nodes):
    print("#################### Decision Tree ####################")
    print("Criterion:", decision_tree.criterion, end="\n")
    print("Prune:", decision_tree.prune)
    print("#######################################################")
    
    print("", end="\n")
    for element in tree_nodes:
        print(element,end="\n\t")
        for child in element.childs:
            print(child, end=" ")
        print("", end="\n")        


decision_tree = DecisionTreeREPrune("erro", False)

decision_tree.fit(xdata, ydata)
