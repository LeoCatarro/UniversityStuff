"""
        KNN Algorithm
"""
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix
from sklearn.utils import resample


iris_dataset = load_iris()

X_train, X_test, y_train, y_test = train_test_split(iris_dataset['data'], iris_dataset['target'], random_state=0)
knn = KNeighborsClassifier(n_neighbors=1)
knn.fit(X_train, y_train)
pred_knn = knn.predict(X_test)
confusion = confusion_matrix(y_test, pred_knn)

print("Confusion matrix(Original):\n{}".format(confusion))
print("Test set accuracy: {:.2f}".format(knn.score(X_test, y_test)))

"""
    Resample(100 samples)
"""
X_train2, y_train2 = resample(X_train,y_train, n_samples=100)
knn2 = KNeighborsClassifier(n_neighbors=1)
knn2.fit(X_train2, y_train2)
pred_knn2 = knn2.predict(X_test)
confusion2 = confusion_matrix(y_test, pred_knn2)

print("Confusion matrix(100 Samples):\n{}".format(confusion))
print("Test set accuracy: {:.2f}".format(knn.score(X_test, y_test)))


"""
    Resample(80 samples)
"""
X_train3, y_train3 = resample(X_train,y_train, n_samples=80)
knn3 = KNeighborsClassifier(n_neighbors=1)
knn3.fit(X_train3, y_train3)
pred_knn3 = knn.predict(X_test)
confusion3= confusion_matrix(y_test, pred_knn3)

print("Confusion matrix(100 Samples):\n{}".format(confusion))
print("Test set accuracy: {:.2f}".format(knn3.score(X_test, y_test)))



"""
    Resample(60 samples)
"""


"""
    Resample(40 samples)
"""


"""
    Resample(20 samples)
"""